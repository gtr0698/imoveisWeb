package com.projeto.imoveis.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.imoveis.enums.Genero;
import com.projeto.imoveis.enums.Papeis;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class PessoaFisica extends Pessoa{

    private String cpf;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Papeis papel;


    public PessoaFisica(String nome, String email, String cpf, Genero genero, LocalDate dataNascimento, Papeis papel) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.papel = papel;
    }

    public PessoaFisica() {

    }

    public String getCpf() {
        return cpf;
    }

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Papeis getPapel() {
        return papel;
    }

    public PessoaFisica atualizaPessoaF(String nome, String email, String cpf, Genero genero, LocalDate dataNascimento, Papeis papel) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.papel = papel;
        return this;
    }
}
