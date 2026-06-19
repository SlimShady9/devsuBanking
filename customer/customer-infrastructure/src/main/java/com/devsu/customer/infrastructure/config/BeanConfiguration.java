package com.devsu.customer.infrastructure.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsu.customer.adapter.controller.CreateCustomerController;
import com.devsu.customer.adapter.controller.DeleteCustomerController;
import com.devsu.customer.adapter.controller.FindAllCustomersController;
import com.devsu.customer.adapter.controller.FindCustomerContoller;
import com.devsu.customer.adapter.controller.UpdateCustomerController;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.repository.PasswordHasher;
import com.devsu.customer.ports.EventPublisher;
import com.devsu.customer.usecase.CreateCustomer;
import com.devsu.customer.usecase.DeleteCustomer;
import com.devsu.customer.usecase.FindAllCustomers;
import com.devsu.customer.usecase.FindCustomerById;
import com.devsu.customer.usecase.UpdateCustomer;

@Configuration
public class BeanConfiguration {

    @Bean
    CreateCustomer createCustomer(CustomerRepository customerRepository, PasswordHasher passwordHasher,
            EventPublisher eventPublisher) {
        return new CreateCustomer(customerRepository, passwordHasher, eventPublisher);
    }

    @Bean
    CreateCustomerController createClientController(CreateCustomer createCustomer) {
        return new CreateCustomerController(createCustomer);
    }

    @Bean
    DeleteCustomer deleteCustomer(CustomerRepository customerRepository, EventPublisher eventPublisher) {
        return new DeleteCustomer(customerRepository, eventPublisher);
    }

    @Bean
    DeleteCustomerController deleteCustomerController(DeleteCustomer deleteCustomer) {
        return new DeleteCustomerController(deleteCustomer);
    }

    @Bean
    UpdateCustomer updateCustomer(CustomerRepository customerRepository) {
        return new UpdateCustomer(customerRepository);
    }

    @Bean
    UpdateCustomerController updateCustomerController(UpdateCustomer updateCustomer) {
        return new UpdateCustomerController(updateCustomer);
    }

    @Bean
    FindAllCustomers findAllCustomers(CustomerRepository customerRepository) {
        return new FindAllCustomers(customerRepository);
    }

    @Bean
    FindAllCustomersController findAllCustomersController(FindAllCustomers findAllCustomers) {
        return new FindAllCustomersController(findAllCustomers);
    }

    @Bean
    FindCustomerById findCustomerById(CustomerRepository customerRepository) {
        return new FindCustomerById(customerRepository);
    }

    @Bean
    FindCustomerContoller findCustomerContoller(FindCustomerById findCustomerById) {
        return new FindCustomerContoller(findCustomerById);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
