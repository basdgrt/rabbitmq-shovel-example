package com.github.bdg91.rabbitmq.message;

import com.github.bdg91.rabbitmq.property.RabbitMqProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties rabbitMqProperties;

    public MessageProducer(final RabbitTemplate rabbitTemplate, final RabbitMqProperties rabbitMqProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqProperties = rabbitMqProperties;
    }

    public void produceMessages(final int amountOfMessages) {
        for (int i = 0; i < amountOfMessages; i++) {
            rabbitTemplate.convertAndSend(rabbitMqProperties.getSourceQueueName(), "message " + i);
        }
    }
}
