package com.projeto.imoveis.dto.pessoaJ;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.PessoaJuridica;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Getter
public class CreatePessoaJuridicaDto {

    @Size(min = 3, max = 50)
    private String nome;

    @Email
    @Size(min = 3, max = 50)
    private String email;

    private String razaoSocial;
    private String nomeFantasia;

    private String cnpj;

    private Papeis papel;

    public CreatePessoaJuridicaDto(String nome, String email, String razaoSocial, String nomeFantasia, String cnpj, Papeis papel) {
        this.nome = nome;
        this.email = email;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.papel = papel;
    }

    public PessoaJuridica convertToModel(){
        return new PessoaJuridica(nome, email, razaoSocial, nomeFantasia, cnpj, papel);
    }

}
