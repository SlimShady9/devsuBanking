package com.devsu.customer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.exchange}")
    private String EXCHANGE;

    @Bean
    public TopicExchange customerEventExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue customerCreatedQueue(@Value("${spring.rabbitmq.queue.routingCustomerCreatedKey}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    public Queue customerDeletedQueue(@Value("${spring.rabbitmq.queue.routingCustomerDeletedKey}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    public Binding customerCreatedBinding(TopicExchange customerEventExchange,
            Queue customerCreatedQueue) {
        return BindingBuilder.bind(customerCreatedQueue)
                .to(customerEventExchange)
                .with("${spring.rabbitmq.queue.customerCreatedKey}");
    }

    @Bean
    public Binding customerDeletedBinding(TopicExchange customerEventExchange,
            Queue customerDeletedQueue) {
        return BindingBuilder.bind(customerDeletedQueue)
                .to(customerEventExchange)
                .with("${spring.rabbitmq.queue.customerDeletedKey}");
    }
}