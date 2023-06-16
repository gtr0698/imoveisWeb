package com.projeto.imoveis.dto.agendamento;

import com.projeto.imoveis.models.Agendamento;
import com.projeto.imoveis.models.Funcionario;
import com.projeto.imoveis.models.Pessoa;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateAgendamentoDto {

    @NotNull
    private LocalDate dataAgendamento;

    @NotNull
    private List<Pessoa> pessoa;

    @NotNull
    private List<Funcionario> funcionario;

    public CreateAgendamentoDto(LocalDate dataAgendamento, List<Pessoa> pessoa, List<Funcionario> funcionario) {
        this.dataAgendamento = dataAgendamento;
        this.pessoa = pessoa;
        this.funcionario = funcionario;
    }

    public Agendamento convertToModel(){
        return new Agendamento(dataAgendamento, pessoa, funcionario);
    }
}
