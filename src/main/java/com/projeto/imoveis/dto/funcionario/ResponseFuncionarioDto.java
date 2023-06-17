package com.projeto.imoveis.dto.funcionario;

import com.projeto.imoveis.enums.TipoPessoa;
import com.projeto.imoveis.models.Funcionario;
import lombok.Getter;

@Getter
public class ResponseFuncionarioDto {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;
    private String cargo;

    public ResponseFuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getIdFuncionario();
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.telefone = funcionario.getTelefone();
        this.numeroDocumento = funcionario.getNumeroDocumento();
        this.tipoPessoa = funcionario.getTipoPessoa();
        this.cargo = funcionario.getCargo();
    }
}
