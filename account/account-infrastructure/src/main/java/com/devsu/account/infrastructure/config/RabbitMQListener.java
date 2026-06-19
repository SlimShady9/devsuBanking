package com.devsu.account.infrastructure.config;

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

    @RabbitListener(queues = "${spring.rabbitmq.queue.customerCreatedKey}")
    public void listenCustomerCreated(@Payload CustomerCreated event) {
        log.info("Customer created event received: {}", event);
        freeAccountDepositUseCase.execute(event.clientId());
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.customerUpdatedKey}")
    public void listenCustomerDeleted(@Payload CustomerDeleted event) {
        log.info("Customer deleted event received: {}", event);
        deletionOfCustomerUseCase.execute(event.clientId());

    }

}
