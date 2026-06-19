package com.devsu.account.infrastructure.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsu.account.adapter.controller.CreateAccountController;
import com.devsu.account.adapter.controller.CreateMovementController;
import com.devsu.account.adapter.controller.FindAccountController;
import com.devsu.account.adapter.controller.FindAllAccountsController;
import com.devsu.account.adapter.controller.FindAllMovementsController;
import com.devsu.account.adapter.controller.FindMovementContoller;
import com.devsu.account.adapter.controller.UpdateAccountController;
import com.devsu.account.adapter.viewmodel.AccountRequestModel;
import com.devsu.account.adapter.viewmodel.AccountViewModel;
import com.devsu.account.adapter.viewmodel.MovementRequestModel;
import com.devsu.account.adapter.viewmodel.MovementViewModel;

import java.util.List;

@RestController
@RequestMapping("/api/movement")
@Slf4j
public class MovementController {

    private final CreateMovementController createMovementController;
    private final FindAllMovementsController findAllMovementsController;
    private final FindMovementContoller findMovementController;

    public MovementController(CreateMovementController createMovementController,
            FindAllMovementsController findAllMovementsController,
            FindMovementContoller findMovementController) {
        this.createMovementController = createMovementController;
        this.findAllMovementsController = findAllMovementsController;
        this.findMovementController = findMovementController;
    }

    @PostMapping
    public ResponseEntity<MovementViewModel> createMovement(@RequestBody MovementRequestModel movementRequestModel) {
        log.info("Creating movement: {}", movementRequestModel);
        createMovementController.handle(movementRequestModel.getAccountNumber(), movementRequestModel.getAmount());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MovementViewModel>> getAllMovements(@RequestParam String accountNumber) {
        log.info("Getting all movements for account number: {}", accountNumber);
        return ResponseEntity.ok(findAllMovementsController.handle(accountNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementViewModel> getMovement(@PathVariable String id) {
        log.info("Getting movement: {}", id);
        return ResponseEntity.ok(findMovementController.handle(id));
    }

}
