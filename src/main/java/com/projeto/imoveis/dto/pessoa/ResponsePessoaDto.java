package com.projeto.imoveis.dto.pessoa;

import com.projeto.imoveis.enums.TipoPessoa;
import com.projeto.imoveis.models.Pessoa;
import lombok.Getter;

@Getter
public class ResponsePessoaDto {

    private Long idPessoa;
    private String nome;
    private String email;
    private String telefone;
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;


    public ResponsePessoaDto(Pessoa pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();
        this.telefone = pessoa.getTelefone();
        this.numeroDocumento = pessoa.getNumeroDocumento();
        this.tipoPessoa = pessoa.getTipoPessoa();
    }
}
