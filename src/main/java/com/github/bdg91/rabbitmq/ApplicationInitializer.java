package com.github.bdg91.rabbitmq;

import com.github.bdg91.rabbitmq.message.MessageProducer;
import com.github.bdg91.rabbitmq.property.RabbitMqProperties;
import com.rabbitmq.http.client.Client;
import com.rabbitmq.http.client.domain.ShovelDetails;
import com.rabbitmq.http.client.domain.ShovelInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationInitializer {

    private final Client client;
    private final RabbitMqProperties rabbitMqProperties;
    private final MessageProducer messageProducer;

    public ApplicationInitializer(final Client client, final RabbitMqProperties rabbitMqProperties,
                                  final MessageProducer messageProducer) {
        this.client = client;
        this.rabbitMqProperties = rabbitMqProperties;
        this.messageProducer = messageProducer;
    }

    @PostConstruct
    public void createShovel() {
        String sourceUri = rabbitMqProperties.getShovel().getSourceUri();
        String destinationUri = rabbitMqProperties.getShovel().getDestinationUri();
        String shovelName = rabbitMqProperties.getShovel().getShovelName();

        var shovelDetails = new ShovelDetails(sourceUri, destinationUri, 60L, false, null);
        shovelDetails.setSourceQueue(rabbitMqProperties.getSourceQueueName());
        shovelDetails.setDestinationQueue(rabbitMqProperties.getDestinationQueueName());

        var shovelInfo = new ShovelInfo(shovelName, shovelDetails);
        client.declareShovel("/", shovelInfo);
    }

    @PostConstruct
    public void putMessagesOnSourceQueue() {
        int amountOfMessages = 10;
        messageProducer.produceMessages(amountOfMessages);
    }
}
