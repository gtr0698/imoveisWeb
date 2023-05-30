package com.projeto.imoveis.dto.funcionario;

import com.projeto.imoveis.enums.Papeis;
import com.projeto.imoveis.enums.TipoPessoa;
import com.projeto.imoveis.models.CadFuncionario;
import lombok.Getter;

@Getter
public class ResponseFuncionarioDto {

    private String nome;
    private String email;
    private String telefone;
    private String numeroDocumento;
    private TipoPessoa tipoPessoa;
    private Papeis papel;
    private String cargo;

    public ResponseFuncionarioDto(CadFuncionario funcionario) {
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.telefone = funcionario.getTelefone();
        this.numeroDocumento = funcionario.getNumeroDocumento();
        this.tipoPessoa = funcionario.getTipoPessoa();
        this.papel = funcionario.getPapel();
        this.cargo = funcionario.getCargo();
    }
}
