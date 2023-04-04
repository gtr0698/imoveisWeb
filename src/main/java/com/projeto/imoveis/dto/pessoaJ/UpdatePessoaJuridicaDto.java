package com.projeto.imoveis.dto.pessoaJ;

import com.projeto.imoveis.enums.Papeis;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePessoaJuridicaDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String email;

    private String razaoSocial;
    private String nomeFantasia;
    @NotBlank
    private String cnpj;
    @NotBlank
    private Papeis papel;

    public UpdatePessoaJuridicaDto(){
        super();
    }
}
