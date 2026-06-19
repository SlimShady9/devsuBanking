package com.devsu.customer.infrastructure.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.devsu.customer.ports.EventPublisher;

@Component
public class RabbitMQPublisher implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String EXCHANGE;

    @Value("${spring.rabbitmq.queue.routingCreatedCustomerKey}")
    private String ROUTING_KEY_CREATED;

    @Value("${spring.rabbitmq.queue.routingCustomerDeletedKey}")
    private String ROUTING_KEY_DELETED;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishCustomerCreated(Object event) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CREATED, event);
    }

    @Override
    public void notifyCustomerDeleted(Object event) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_DELETED, event);
    }
}
