package com.github.bdg91.rabbitmq.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "rabbitmq")
@ConstructorBinding
public class RabbitMqProperties {

    private final String username;
    private final String password;
    private final String url;
    private final String sourceQueueName;
    private final String destinationQueueName;
    private final boolean durable;
    private final ShovelProperties shovel;

    public RabbitMqProperties(final String username, final String password, final String url, final String sourceQueueName,
                              final String destinationQueueName, final boolean durable, final ShovelProperties shovel) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.sourceQueueName = sourceQueueName;
        this.destinationQueueName = destinationQueueName;
        this.durable = durable;
        this.shovel = shovel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getSourceQueueName() {
        return sourceQueueName;
    }

    public String getDestinationQueueName() {
        return destinationQueueName;
    }

    public boolean isDurable() {
        return durable;
    }

    public ShovelProperties getShovel() {
        return shovel;
    }

    public static class ShovelProperties {
        private final String sourceUri;
        private final String destinationUri;
        private final String shovelName;

        public ShovelProperties(final String sourceUri, final String destinationUri, final String shovelName) {
            this.sourceUri = sourceUri;
            this.destinationUri = destinationUri;
            this.shovelName = shovelName;
        }

        public String getSourceUri() {
            return sourceUri;
        }

        public String getDestinationUri() {
            return destinationUri;
        }

        public String getShovelName() {
            return shovelName;
        }
    }
}
