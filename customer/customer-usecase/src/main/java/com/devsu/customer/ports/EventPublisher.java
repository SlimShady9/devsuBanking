package com.devsu.customer.ports;

public interface EventPublisher {

    void publish(Object event);
}
