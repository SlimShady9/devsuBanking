package com.devsu.customer.infrastructure.rest.controller;

import com.devsu.customer.adapter.controller.CreateCustomerController;
import com.devsu.customer.adapter.controller.DeleteCustomerController;
import com.devsu.customer.adapter.controller.FindAllCustomersController;
import com.devsu.customer.adapter.controller.FindCustomerContoller;
import com.devsu.customer.adapter.controller.UpdateCustomerController;
import com.devsu.customer.adapter.viewmodel.CustomerRequestModel;
import com.devsu.customer.adapter.viewmodel.CustomerViewModel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CreateCustomerController createCustomerController;
    private final FindAllCustomersController findAllCustomersController;
    private final FindCustomerContoller findCustomerContoller;
    private final UpdateCustomerController updateCustomerController;
    private final DeleteCustomerController deleteCustomerController;

    public CustomerController(CreateCustomerController createCustomerController,
            FindAllCustomersController findAllCustomersController,
            FindCustomerContoller findCustomerContoller,
            UpdateCustomerController updateCustomerController,
            DeleteCustomerController deleteCustomerController) {
        this.createCustomerController = createCustomerController;
        this.findAllCustomersController = findAllCustomersController;
        this.findCustomerContoller = findCustomerContoller;
        this.updateCustomerController = updateCustomerController;
        this.deleteCustomerController = deleteCustomerController;
    }

    @PostMapping
    public ResponseEntity<CustomerRequestModel> createCustomer(@RequestBody CustomerRequestModel customerViewModel) {
        log.info("Creating customer: {}", customerViewModel);
        createCustomerController.handle(customerViewModel.getName(), customerViewModel.getGender(),
                customerViewModel.getAge(), customerViewModel.getState(), customerViewModel.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerViewModel>> getAllCustomers() {
        log.info("Getting all customers");
        return ResponseEntity.ok(findAllCustomersController.handle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerViewModel> getCustomer(@PathVariable String id) {
        log.info("Getting customer: {}", id);
        return ResponseEntity.ok(findCustomerContoller.handle(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerViewModel> updateCustomer(@PathVariable String id,
            @RequestBody CustomerRequestModel customerRequestModel) {
        log.info("Updating customer: {}", id);
        updateCustomerController.handle(id, customerRequestModel.getName(), customerRequestModel.getGender(),
                customerRequestModel.getAge(), customerRequestModel.getState());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerViewModel> deleteCustomer(@PathVariable String id) {
        log.info("Deleting customer: {}", id);
        deleteCustomerController.handle(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
