package com.projeto.imoveis.dto.funcionario;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.CadFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateFuncionarioDto {

    @Size(min = 3, max = 50)
    private String nome;
    @Email
    @Size(min = 3, max = 50)
    private String email;
    private Papeis papel;
    @NotBlank
    private String cargo;
    @NotBlank
    private String senha;

    public CreateFuncionarioDto(String nome, String email, Papeis papel, String cargo, String senha) {
        this.nome = nome;
        this.email = email;
        this.papel = papel;
        this.cargo = cargo;
        this.senha = senha;
    }

    public CadFuncionario convertToModel(){
        return new CadFuncionario(nome, email, papel, cargo, senha);
    }
}
