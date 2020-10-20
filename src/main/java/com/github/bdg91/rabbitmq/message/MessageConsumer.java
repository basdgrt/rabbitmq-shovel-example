package com.github.bdg91.rabbitmq.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @SuppressWarnings("unused") // Used by the MessageListenerAdapter via reflection
    public void handleMessage(final String message) {
        LOGGER.info("Received: [{}]", message);
    }
}
