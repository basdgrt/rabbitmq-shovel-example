package com.github.bdg91.rabbitmq.configuration;

import com.github.bdg91.rabbitmq.message.MessageConsumer;
import com.github.bdg91.rabbitmq.property.RabbitMqProperties;
import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.ClientParameters;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Configuration
public class RabbitMqConfiguration {

    // Creates a queue that will receive messages from our producer. The shovel will move messages from this queue to
    // the destination queue.
    @Bean
    Queue sourceQueue(final RabbitMqProperties rabbitMqProperties) {
        return new Queue(rabbitMqProperties.getSourceQueueName(), rabbitMqProperties.isDurable());
    }

    // Creates a queue from which our consumer will consume messages. The shovel will move messages from the source
    // queue to this queue.
    @Bean
    Queue destinationQueue(final RabbitMqProperties rabbitMqProperties) {
        return new Queue(rabbitMqProperties.getDestinationQueueName(), rabbitMqProperties.isDurable());
    }

    // Creates a container that listens for messages on the destination Queue.
    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory,
                                             final MessageListenerAdapter listenerAdapter,
                                             final RabbitMqProperties rabbitMqProperties) {
        var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitMqProperties.getDestinationQueueName());
        container.setMessageListener(listenerAdapter);
        return container;
    }

    // Registers a message listener.
    @Bean
    MessageListenerAdapter listenerAdapter(final MessageConsumer messageConsumer) {
        return new MessageListenerAdapter(messageConsumer);
    }

    // Creates a Rabbit http client which will be used to create the shovel. The actual shovel is created in the
    // ApplicationInitializer class.
    @Bean
    Client client(final RabbitMqProperties rabbitMqProperties) throws MalformedURLException, URISyntaxException {
        var clientParameters = new ClientParameters()
                .url(rabbitMqProperties.getUrl() + "/api")
                .username(rabbitMqProperties.getUsername())
                .password(rabbitMqProperties.getPassword());

        return new Client(clientParameters);
    }
}
