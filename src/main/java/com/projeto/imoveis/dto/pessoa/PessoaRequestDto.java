package com.projeto.imoveis.dto.pessoa;

import lombok.Getter;

@Getter
public class PessoaRequestDto {

    private Long idPessoa;

    public PessoaRequestDto(Long id) {
        this.idPessoa = id;
    }

    public PessoaRequestDto() {
    }
}
