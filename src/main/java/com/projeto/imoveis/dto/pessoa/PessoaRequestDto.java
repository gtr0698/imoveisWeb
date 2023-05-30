package com.projeto.imoveis.dto.pessoa;

import lombok.Getter;

@Getter
public class PessoaRequestDto {

    private Long id;

    public PessoaRequestDto(Long id) {
        this.id = id;
    }

    public PessoaRequestDto() {
    }
}
