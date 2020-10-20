# RabbitMQ Shovel Example
Example Spring Boot application that demonstrates how to use RabbitMQ
shovels. This example will create a source and destination queue with 
a shovel to move messages between them.

This repository is part of the Medium post: 
[Everyday I'm Shovelling - How to use RabbitMQ shovels with Java and Spring Boot](https://medium.com/@bdg91/everyday-im-shovelling-1c79b6d568d7)

## RabbitMQ setup
The easiest way to set up a RabbitMQ environment is via Docker. 
However since the standard RabbitMQ image does not enable the 
`rabbitmq_shovel` plugin by default we need to use a custom image. An
image with `rabbitmq_shovel` enabled is available on this 
[Docker Hub](https://hub.docker.com/repository/docker/bdg91/rabbitmq-shovel) repository.

To run the image:

`docker run --name rabbitmq-shovel --rm -d -it --hostname my-rabbit -p 15672:15672 -p 5672:5672 bdg91/rabbitmq-shovel:latest`
 