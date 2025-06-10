package com.test.security.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String nome;

    @Column(nullable = false, unique = true, length = 256)
    private String email;

    @Column(nullable = false, length = 256)
    private String senha; // Remember to hash this before saving!
    //
}
