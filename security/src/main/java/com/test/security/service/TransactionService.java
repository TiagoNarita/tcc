package com.test.security.service;

import com.test.security.controller.dto.TransactionDTO;
import com.test.security.domain.entity.Transaction;
import com.test.security.domain.entity.User;

public interface TransactionService {
    Transaction createTransaction(TransactionDTO transactionDTO, User user);
}
