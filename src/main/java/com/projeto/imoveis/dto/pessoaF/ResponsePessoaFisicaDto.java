package com.projeto.imoveis.dto.pessoaF;

import com.projeto.imoveis.enums.Genero;
import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.PessoaFisica;

import java.time.LocalDate;

public class ResponsePessoaFisicaDto {

    private Long idPessoa;
    private String nome;
    private String email;
    private String cpf;
    private Genero genero;
    private LocalDate dataNascimento;

    private Papeis papel;

    public ResponsePessoaFisicaDto(PessoaFisica pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.cpf = pessoa.getCpf();
        this.genero = pessoa.getGenero();
        this.dataNascimento = pessoa.getDataNascimento();
        this.papel = pessoa.getPapel();
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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
}
