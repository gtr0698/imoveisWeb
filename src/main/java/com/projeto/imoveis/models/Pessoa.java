package com.projeto.imoveis.models;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    protected String nome;

    protected String email;

    protected String telefone;

    @Size(min = 11, max = 14)
    protected String numeroDocumento;

    @Enumerated(EnumType.STRING)
    protected TipoPessoa tipoPessoa;

    @Enumerated(EnumType.STRING)
    protected Papeis papel;

    protected String senha;

    public Pessoa(String nome, String email, String telefone, String numeroDocumento, TipoPessoa tipoPessoa,
                  Papeis papel, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.papel = papel;
        this.senha = senha;
    }

    public Pessoa() {
        super();
    }

    public Pessoa atualizaPessoa(String nome, String email, String telefone, String numeroDocumento,
                                 TipoPessoa tipoPessoa, Papeis papel, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.papel = papel;
        this.senha = senha;
        return this;
    }
}
