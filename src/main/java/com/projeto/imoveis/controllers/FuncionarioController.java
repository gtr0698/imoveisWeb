package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.funcionario.ResponseFuncionarioDto;
import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listaFuncionarios(){
        List<Pessoa> funcionarios = funcionarioService.listarTodosFunc();
        if(funcionarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
    }

    /*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/buscar/{funcionarioId}")
    public ResponseEntity<Object>buscarFuncionario(@PathVariable(value = "funcionarioId") Long funcionarioId){
        Optional<Pessoa> buscarFuncionario = funcionarioService.localizarFunc(funcionarioId);
        if(!buscarFuncionario.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("registro não localizado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarFuncionario);
    }
    */

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/criar")
    public ResponseEntity<ResponseFuncionarioDto>adicionarFuncionario(@Valid @RequestBody CreatePessoaDto funcionario){
        ResponseFuncionarioDto funcionarioSalvo = new ResponseFuncionarioDto(funcionarioService.salvarFunc(funcionario));

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    /*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/atualizar/{funcionarioId}")
    public ResponseEntity<ResponseFuncionarioDto>atualizarFuncionario(@Valid @PathVariable Long funcionarioId,
                                                                        @RequestBody UpdatePessoaDto funcionario){
        ResponseFuncionarioDto funcionarioSalvo = new ResponseFuncionarioDto(funcionarioService.atualizarFunc(funcionarioId,
                funcionario));

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioSalvo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/remover/{funcionarioId}")
    public ResponseEntity<?> removeFuncionario(@PathVariable Long funcionarioId){
        funcionarioService.excluirFunc(funcionarioId);

        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso!");
    }
    */

}
