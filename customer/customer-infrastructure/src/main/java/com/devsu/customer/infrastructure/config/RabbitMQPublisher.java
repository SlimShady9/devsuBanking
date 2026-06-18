package com.devsu.customer.infrastructure.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.devsu.customer.ports.EventPublisher;

@Component
public class RabbitMQPublisher implements EventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String EXCHANGE = "customer.event";
    private final String ROUTING_KEY = "customer.created";

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(Object event) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, event);
    }
}
