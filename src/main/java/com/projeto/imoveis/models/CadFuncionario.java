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
public class CadFuncionario extends Pessoa{

    @Enumerated(EnumType.STRING)
    private Papeis papel;
    private String cargo;
    private String usuario;
    private String senha;

    public CadFuncionario(String nome, String email, Papeis papel, String cargo, String usuario, String senha) {
        this.nome = nome;
        this.email = email;
        this.papel = papel;
        this.cargo = cargo;
        this.usuario = usuario;
        this.senha = senha;
    }

    public CadFuncionario atualizaCadFuncionario(String nome, String email, Papeis papel, String cargo, String usuario, String senha) {
        this.nome = nome;
        this.email = email;
        this.papel = papel;
        this.cargo = cargo;
        this.usuario = usuario;
        this.senha = senha;
        return this;
    }

    public CadFuncionario() {
    }
}
