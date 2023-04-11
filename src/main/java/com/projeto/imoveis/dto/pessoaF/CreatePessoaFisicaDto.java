package com.projeto.imoveis.dto.pessoaF;

import com.projeto.imoveis.enums.Genero;
import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.PessoaFisica;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreatePessoaFisicaDto {

    @Size(min = 3, max = 50)
    private String nome;

    @Email
    @Size(min = 3, max = 50)
    private String email;

    @NotBlank
    private String cpf;

    private Genero genero;

    private LocalDate dataNascimento;

    private Papeis papel;

    public CreatePessoaFisicaDto(String nome, String email, String cpf, Genero genero, LocalDate dataNascimento, Papeis papel) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.papel = papel;
    }

    public PessoaFisica convertToModel(){
        return new PessoaFisica(nome, email, cpf, genero, dataNascimento, papel);
    }
}
