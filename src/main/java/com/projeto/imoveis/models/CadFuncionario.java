package com.projeto.imoveis.models;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.enums.TipoPessoa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class CadFuncionario extends Pessoa{

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa funcionarioId;

    private String cargo;


    public CadFuncionario(String nome, String email, String telefone, String numeroDocumento,
                          TipoPessoa tipoPessoa, Papeis papel, String cargo, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.papel = papel;
        this.cargo = cargo;
        this.senha = senha;
    }


    public CadFuncionario atualizaCadFuncionario(String nome, String email, String telefone, String numeroDocumento,
                                                 TipoPessoa tipoPessoa, Papeis papel, String cargo, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.papel = papel;
        this.cargo = cargo;
        this.senha = senha;
        return this;
    }

    public CadFuncionario() {
    }
}
