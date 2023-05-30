package com.projeto.imoveis.dto.pessoa;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.enums.TipoPessoa;
import com.projeto.imoveis.models.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CreatePessoaDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    @Size(min = 11, max = 14)
    private String numeroDocumento;

    private TipoPessoa tipoPessoa;

    private Papeis papel;

    @NotBlank
    @Size(min = 6, max = 15)
    private String senha;

    public CreatePessoaDto(String nome, String email, String telefone, String numeroDocumento,
                           TipoPessoa tipoPessoa, Papeis papel, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.papel = papel;
        this.senha = senha;
    }

    public Pessoa convertToModel(){
        return new Pessoa(nome, email, telefone, numeroDocumento, tipoPessoa, papel, senha);
    }
}
