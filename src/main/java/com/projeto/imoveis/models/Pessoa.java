package com.projeto.imoveis.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPessoa;

    @Column(nullable = false, unique = true, length = 50)
    protected String nome;

    @Column(nullable = false, unique = true, length = 50)
    protected String email;

}
