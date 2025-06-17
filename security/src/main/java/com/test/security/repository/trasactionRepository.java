package com.test.security.repository;

import com.test.security.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface trasactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUsuarioId(Long usuarioId);
}
