package com.projeto.imoveis.dto.pessoaF;

import com.projeto.imoveis.enums.Genero;
import com.projeto.imoveis.enums.Papeis;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdatePessoaFisicaDto {

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
    @NotBlank
    private String senha;

    public UpdatePessoaFisicaDto() {
        super();
    }
}
