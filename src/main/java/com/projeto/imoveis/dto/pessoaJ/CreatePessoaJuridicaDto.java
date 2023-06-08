package com.projeto.imoveis.dto.pessoaJ;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.PessoaJuridica;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreatePessoaJuridicaDto {

    @Size(min = 3, max = 50)
    private String nome;
    @Email
    @Size(min = 3, max = 50)
    private String email;
    private String razaoSocial;
    @NotBlank
    private String cnpj;
    private Papeis papel;
    @NotBlank
    private String senha;

    public CreatePessoaJuridicaDto(String nome, String email, String razaoSocial, String cnpj, Papeis papel, String senha) {
        this.nome = nome;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.papel = papel;
        this.senha = senha;
    }

    public PessoaJuridica convertToModel(){
        return new PessoaJuridica(nome, email, razaoSocial, cnpj, papel, senha);
    }

}
