package com.devsu.account.infrastructure.config;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.devsu.account.adapter.ports.CustomerCreated;
import com.devsu.account.adapter.ports.CustomerDeleted;
import com.devsu.account.usecase.DeletionOfCustomerUseCase;
import com.devsu.account.usecase.FreeAccountDepositUseCase;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQListener {

    private final FreeAccountDepositUseCase freeAccountDepositUseCase;
    private final DeletionOfCustomerUseCase deletionOfCustomerUseCase;

    public RabbitMQListener(FreeAccountDepositUseCase freeAccountDepositUseCase,
            DeletionOfCustomerUseCase deletionOfCustomerUseCase) {
        this.freeAccountDepositUseCase = freeAccountDepositUseCase;
        this.deletionOfCustomerUseCase = deletionOfCustomerUseCase;
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${spring.rabbitmq.queue.routingCustomerCreatedKey}", durable = "false"), exchange = @Exchange(value = "${spring.rabbitmq.exchange}", type = ExchangeTypes.TOPIC), key = "${spring.rabbitmq.queue.routingCustomerCreatedKey}"))
    public void listenCustomerCreated(@Payload CustomerCreated event) {
        log.info("Customer created event received: {}", event);
        freeAccountDepositUseCase.execute(event.customerId());
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${spring.rabbitmq.queue.routingCustomerDeletedKey}", durable = "false"), exchange = @Exchange(value = "${spring.rabbitmq.exchange}", type = ExchangeTypes.TOPIC), key = "${spring.rabbitmq.queue.routingCustomerDeletedKey}"))
    public void listenCustomerDeleted(@Payload CustomerDeleted event) {
        log.info("Customer deleted event received: {}", event);
        deletionOfCustomerUseCase.execute(event.customerId());

    }

}
