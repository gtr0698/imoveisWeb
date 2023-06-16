package com.projeto.imoveis.dto.agendamento;

import com.projeto.imoveis.models.Agendamento;
import com.projeto.imoveis.models.Funcionario;
import com.projeto.imoveis.models.Pessoa;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResponseAgendamentoDto {

    private LocalDate dataAgendamento;
    private List<Pessoa> pessoa;
    private List<Funcionario> funcionario;

    public ResponseAgendamentoDto(Agendamento agendamento) {
        this.dataAgendamento = agendamento.getDataAgendamento();
        this.pessoa = agendamento.getPessoa();
        this.funcionario = agendamento.getFuncionario();
    }
}
