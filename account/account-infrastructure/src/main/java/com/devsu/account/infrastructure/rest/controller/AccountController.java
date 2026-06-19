package com.devsu.account.infrastructure.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.account.adapter.controller.CreateAccountController;
import com.devsu.account.adapter.controller.FindAccountController;
import com.devsu.account.adapter.controller.FindAllAccountsController;
import com.devsu.account.adapter.controller.UpdateAccountController;
import com.devsu.account.adapter.viewmodel.AccountRequestModel;
import com.devsu.account.adapter.viewmodel.AccountViewModel;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    private final CreateAccountController createAccountController;
    private final FindAllAccountsController findAllAccountsController;
    private final FindAccountController findAccountController;
    private final UpdateAccountController updateAccountController;

    public AccountController(CreateAccountController createAccountController,
            FindAllAccountsController findAllAccountsController,
            FindAccountController findAccountController,
            UpdateAccountController updateAccountController) {
        this.createAccountController = createAccountController;
        this.findAllAccountsController = findAllAccountsController;
        this.findAccountController = findAccountController;
        this.updateAccountController = updateAccountController;
    }

    @PostMapping
    public ResponseEntity<AccountViewModel> createAccount(@RequestBody AccountRequestModel accountViewModel) {
        log.info("Creating account: {}", accountViewModel);
        createAccountController.handle(accountViewModel.getCustomerId(), accountViewModel.getAccountType());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AccountViewModel>> getAllAccounts() {
        log.info("Getting all accounts");
        return ResponseEntity.ok(findAllAccountsController.handle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountViewModel> getAccount(@PathVariable String id) {
        log.info("Getting account: {}", id);
        return ResponseEntity.ok(findAccountController.handle(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountViewModel> updateAccount(@PathVariable String id,
            @RequestBody AccountRequestModel accountRequestModel) {
        log.info("Updating account: {}", id);
        updateAccountController.handle(id, accountRequestModel.getAccountType(), accountRequestModel.getState());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
