package com.devsu.account.infrastructure.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper.TypePrecedence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfiguration {

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        converter.setTypePrecedence(TypePrecedence.TYPE_ID);
        return converter;
    }

}
