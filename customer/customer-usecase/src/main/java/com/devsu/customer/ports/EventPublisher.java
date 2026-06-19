package com.devsu.customer.ports;

public interface EventPublisher {

    void publishCustomerCreated(Object event);

    void notifyCustomerDeleted(Object event);
}
