package com.projeto.imoveis.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataAgendamento;

    @ManyToMany
    @JoinTable(name = "agendamentos_pessoas",
            joinColumns = @JoinColumn(name = "id_agendamento"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
    private List<Pessoa> pessoa;

    @ManyToMany
    @JoinTable(name = "agendamentos_funcionarios",
            joinColumns = @JoinColumn(name = "id_agendamento"),
            inverseJoinColumns = @JoinColumn(name = "funcionarios_id"))
    private List<Funcionario> funcionario;


    public Agendamento(LocalDate dataAgendamento, List<Pessoa> pessoa, List<Funcionario> funcionario) {
        this.dataAgendamento = dataAgendamento;
        this.pessoa = pessoa;
        this.funcionario = funcionario;
    }
}
