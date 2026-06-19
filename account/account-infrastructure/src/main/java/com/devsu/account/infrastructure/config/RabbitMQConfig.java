package com.devsu.account.infrastructure.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.exchange}")
    private String EXCHANGE;

    @Bean
    public TopicExchange customerEventExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }
}