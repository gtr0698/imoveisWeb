package com.projeto.imoveis.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.imoveis.enums.Papeis;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class PessoaJuridica extends Pessoa{

    @Column(nullable = false, unique = true, length = 50)
    private String razaoSocial;

    @Column(nullable = false, unique = true, length = 50)
    private String nomeFantasia;

    @Column(nullable = false, unique = true, length = 30)
    private String cnpj;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Papeis papel;

    public PessoaJuridica(String nome, String email, String razaoSocial, String nomeFantasia, String cnpj, Papeis papel) {
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Papeis getPapel() {
        return papel;
    }

    public PessoaJuridica atualizaPessoaJ(String nome, String email, String razaoSocial, String nomeFantasia, String cnpj, Papeis papel) {
        this.nome = nome;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.papel = papel;
        return this;
    }

    public PessoaJuridica() {
    }
}
