package com.projeto.imoveis.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPessoa;

    protected String nome;

    protected String email;

}
