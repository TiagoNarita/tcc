package com.test.security.repository;

import com.test.security.domain.entity.Transaction;
import com.test.security.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrasactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUsuarioId(Long usuarioId);

    Page<Transaction> findByUsuarioAndDataBetween(User usuario, LocalDateTime start, LocalDateTime end, Pageable pageable);
}
