package com.projeto.imoveis.dto.pessoaJ;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.PessoaJuridica;
import lombok.Getter;

@Getter
public class ResponsePessoaJuridicaDto {

    private Long idPessoa;
    private String nome;
    private String email;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private Papeis papel;

    public ResponsePessoaJuridicaDto(PessoaJuridica pessoa){
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.razaoSocial = pessoa.getRazaoSocial();
        this.nomeFantasia = pessoa.getNomeFantasia();
        this.cnpj = pessoa.getCnpj();
        this.papel = pessoa.getPapel();
    }
}
