package com.projeto.imoveis.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projeto.imoveis.enums.Papeis;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class PessoaJuridica extends Pessoa{

    private String razaoSocial;
    private String cnpj;
    @Enumerated(EnumType.STRING)
    private Papeis papel;

    public PessoaJuridica(String nome, String email, String razaoSocial, String cnpj, Papeis papel, String senha) {
        this.nome = nome;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.papel = papel;
        this.senha = senha;
    }

    public PessoaJuridica() {

    }

    public PessoaJuridica atualizaPessoaJ(String nome, String email, String razaoSocial, String cnpj, Papeis papel, String senha) {
        this.nome = nome;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.papel = papel;
        this.senha = senha;
        return this;
    }
}
