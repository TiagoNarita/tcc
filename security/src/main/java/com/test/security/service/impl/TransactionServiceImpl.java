package com.test.security.service.impl;

import com.test.security.controller.dto.TransactionDTO;
import com.test.security.domain.entity.Category;
import com.test.security.domain.entity.Transaction;
import com.test.security.domain.entity.User;
import com.test.security.repository.CategoryRepository;
import com.test.security.repository.TrasactionRepository;
import com.test.security.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TrasactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Transaction createTransaction(TransactionDTO transactionDTO, User user) {
        // Find the category by its ID, throw an exception if not found
        Category category = categoryRepository.findById(transactionDTO.categoriaId())
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        // Create a new Transaction entity
        Transaction transaction = new Transaction();
        transaction.setDescricao(transactionDTO.descricao());
        transaction.setValor(transactionDTO.valor());
        transaction.setData(transactionDTO.data());
        transaction.setCategoria(category);
        transaction.setTipo(category.getTipo());
        transaction.setUsuario(user); // Set the authenticated user

        // Save the transaction to the database
        return transactionRepository.save(transaction);
    }
}
