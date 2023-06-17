package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.agendamento.CreateAgendamentoDto;
import com.projeto.imoveis.dto.agendamento.ResponseAgendamentoDto;
import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.dto.pessoa.ResponsePessoaDto;
import com.projeto.imoveis.services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE')")
    @PostMapping("/criar")
    public ResponseEntity<ResponseAgendamentoDto> criarAgendamento(@Valid @RequestBody CreateAgendamentoDto agendamento){
        ResponseAgendamentoDto agendamentoSalvo = new ResponseAgendamentoDto(agendamentoService.salvar(agendamento));

        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoSalvo);
    }
}
