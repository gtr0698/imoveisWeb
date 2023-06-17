package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.agendamento.CreateAgendamentoDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.Agendamento;
import com.projeto.imoveis.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;


    public Agendamento salvar(CreateAgendamentoDto agendamento) {

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataAgendamento = agendamento.getDataAgendamento();

        if(dataAgendamento.isBefore(dataAtual)){
            throw new RegraException("A data de agendamento não pode ser anterior a data atual");
        }

        Agendamento novoAgendamento = agendamento.convertToModel();

        return agendamentoRepository.save(novoAgendamento);
    }
}
