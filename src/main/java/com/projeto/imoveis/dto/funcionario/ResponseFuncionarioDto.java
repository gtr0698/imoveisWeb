package com.projeto.imoveis.dto.funcionario;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.models.CadFuncionario;
import lombok.Getter;

@Getter
public class ResponseFuncionarioDto {

    private Long idPessoa;
    private String nome;
    private String email;
    private Papeis papel;
    private String cargo;

    public ResponseFuncionarioDto(CadFuncionario funcionario) {
        this.idPessoa = funcionario.getIdPessoa();
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.papel = funcionario.getPapel();
        this.cargo = funcionario.getCargo();
    }
}
