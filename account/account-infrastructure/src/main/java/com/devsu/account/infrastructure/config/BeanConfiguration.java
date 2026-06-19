package com.devsu.account.infrastructure.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper.TypePrecedence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsu.account.adapter.controller.CreateAccountController;
import com.devsu.account.adapter.controller.CreateMovementController;
import com.devsu.account.adapter.controller.FindAccountController;
import com.devsu.account.adapter.controller.FindAllAccountsController;
import com.devsu.account.adapter.controller.FindAllMovementsController;
import com.devsu.account.adapter.controller.FindMovementContoller;
import com.devsu.account.adapter.controller.UpdateAccountController;
import com.devsu.account.domain.repository.AccountNumberCreator;
import com.devsu.account.domain.repository.AccountRepository;
import com.devsu.account.domain.repository.MovementRepository;
import com.devsu.account.usecase.CreateAccount;
import com.devsu.account.usecase.CreateMovement;
import com.devsu.account.usecase.DeletionOfCustomerUseCase;
import com.devsu.account.usecase.FindAccount;
import com.devsu.account.usecase.FindAccounts;
import com.devsu.account.usecase.FindMovement;
import com.devsu.account.usecase.FindMovements;
import com.devsu.account.usecase.FreeAccountDepositUseCase;
import com.devsu.account.usecase.ReadAccount;
import com.devsu.account.usecase.UpdateAccount;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfiguration {

    @Bean
    public AccountNumberCreator accountNumberCreator() {
        return new AccountNumberCreator();
    }

    @Bean
    public CreateAccount createAccount(AccountRepository accountRepository, AccountNumberCreator accountNumberCreator) {
        return new CreateAccount(accountRepository, accountNumberCreator);
    }

    @Bean
    public CreateMovement createMovement(AccountRepository accountRepository, MovementRepository movementRepository) {
        return new CreateMovement(accountRepository, movementRepository);
    }

    @Bean
    public DeletionOfCustomerUseCase deleteAccount(AccountRepository accountRepository) {
        return new DeletionOfCustomerUseCase(accountRepository);
    }

    @Bean
    public FindAccount findAccount(AccountRepository accountRepository) {
        return new FindAccount(accountRepository);
    }

    @Bean
    public FindAccounts findAccounts(AccountRepository accountRepository) {
        return new FindAccounts(accountRepository);
    }

    @Bean
    public FindMovements findMovements(AccountRepository accountRepository, MovementRepository movementRepository) {
        return new FindMovements(accountRepository, movementRepository);
    }

    @Bean
    public FindMovement findMovement(MovementRepository movementRepository) {
        return new FindMovement(movementRepository);
    }

    @Bean
    public FreeAccountDepositUseCase freeAccountDepositUseCase(AccountRepository accountRepository,
            AccountNumberCreator accountNumberCreator, MovementRepository movementRepository) {
        return new FreeAccountDepositUseCase(accountRepository, accountNumberCreator, movementRepository);
    }

    @Bean
    public ReadAccount readAccount(AccountRepository accountRepository) {
        return new ReadAccount(accountRepository);
    }

    @Bean
    public UpdateAccount updateAccount(AccountRepository accountRepository) {
        return new UpdateAccount(accountRepository);
    }

    @Bean
    public CreateAccountController createAccountController(CreateAccount createAccount) {
        return new CreateAccountController(createAccount);
    }

    @Bean
    public CreateMovementController createMovementController(CreateMovement createMovement) {
        return new CreateMovementController(createMovement);
    }

    @Bean
    public FindAccountController findAccountController(FindAccount findAccount) {
        return new FindAccountController(findAccount);
    }

    @Bean
    public FindAllAccountsController findAllAccountsController(FindAccounts findAccounts) {
        return new FindAllAccountsController(findAccounts);
    }

    @Bean
    public FindAllMovementsController findAllMovementsController(FindMovements findMovements) {
        return new FindAllMovementsController(findMovements);
    }

    @Bean
    public FindMovementContoller findMovementContoller(FindMovement findMovement) {
        return new FindMovementContoller(findMovement);
    }

    @Bean
    public UpdateAccountController updateAccountController(UpdateAccount updateAccount) {
        return new UpdateAccountController(updateAccount);
    }

    @Bean
    public MessageConverter amqpMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTrustedPackages("*");

        converter.setTypePrecedence(TypePrecedence.INFERRED);
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }

}
