package com.test.security.controller;

import com.test.security.controller.dto.TransactionDTO;
import com.test.security.domain.entity.Transaction;
import com.test.security.domain.entity.User;
import com.test.security.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createTransaction(
            @RequestBody TransactionDTO transactionDTO,
            @AuthenticationPrincipal User user) {

        Transaction newTransaction = transactionService.createTransaction(transactionDTO, user);
        return ResponseEntity.status(201).body("Transaction successfully created"); // 201 Created
    }
}
