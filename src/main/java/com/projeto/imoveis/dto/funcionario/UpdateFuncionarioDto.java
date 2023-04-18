package com.projeto.imoveis.dto.funcionario;

import com.projeto.imoveis.enums.Papeis;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateFuncionarioDto {

    @Size(min = 3, max = 50)
    private String nome;
    @Email
    @Size(min = 3, max = 50)
    private String email;
    private Papeis papel;
    @NotBlank
    private String cargo;
    @NotBlank
    private String usuario;
    @NotBlank
    private String senha;

    public UpdateFuncionarioDto() {
        super();
    }
}
