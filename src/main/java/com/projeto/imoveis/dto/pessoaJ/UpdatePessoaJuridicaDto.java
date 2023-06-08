package com.projeto.imoveis.dto.pessoaJ;

import com.projeto.imoveis.enums.Papeis;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePessoaJuridicaDto {

    @Size(min = 3, max = 50)
    private String nome;

    @Email
    @Size(min = 3, max = 50)
    private String email;

    private String razaoSocial;
    @NotBlank
    private String cnpj;
    private Papeis papel;
    private String senha;

    public UpdatePessoaJuridicaDto(){
        super();
    }
}
