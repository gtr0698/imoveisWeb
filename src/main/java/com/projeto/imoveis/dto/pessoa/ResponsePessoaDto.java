package com.projeto.imoveis.dto.pessoa;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.enums.TipoPessoa;
import com.projeto.imoveis.models.Pessoa;
import lombok.Getter;

@Getter
public class ResponsePessoaDto {

    private String nome;
    private String email;
    private String telefone;
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;
    private Papeis papel;


    public ResponsePessoaDto(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.telefone = pessoa.getTelefone();
        this.numeroDocumento = pessoa.getNumeroDocumento();
        this.tipoPessoa = pessoa.getTipoPessoa();
        this.papel = pessoa.getPapel();
    }
}
