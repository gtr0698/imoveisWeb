package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.funcionario.CreateFuncionarioDto;
import com.projeto.imoveis.dto.funcionario.ResponseFuncionarioDto;
import com.projeto.imoveis.dto.funcionario.UpdateFuncionarioDto;
import com.projeto.imoveis.models.CadFuncionario;
import com.projeto.imoveis.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cadastros/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<Page<CadFuncionario>> listaFuncionarios(@PageableDefault(page = 0, size = 10, sort = "idPessoa", direction = Sort.Direction.ASC)
                                                                  Pageable pageable){
        Page<CadFuncionario> funcionarios = funcionarioService.listarTodos(pageable);
        if(funcionarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
    }

    @GetMapping("/{funcionarioId}")
    public ResponseEntity<Object>buscarFuncionario(@PathVariable(value = "funcionarioId") Long funcionarioId){
        Optional<CadFuncionario> buscarFuncionario = funcionarioService.localizar(funcionarioId);
        if(!buscarFuncionario.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("registro não localizado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarFuncionario);
    }

    @PostMapping
    public ResponseEntity<ResponseFuncionarioDto>adicionarFuncionario(@Valid @RequestBody CreateFuncionarioDto funcionario){
        ResponseFuncionarioDto funcionarioSalvo = new ResponseFuncionarioDto(funcionarioService.salvar(funcionario));

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioSalvo);
    }

    @PutMapping("/{funcionarioId}")
    public ResponseEntity<ResponseFuncionarioDto>atualizarFuncionario(@Valid @PathVariable Long funcionarioId,
                                                                        @RequestBody UpdateFuncionarioDto funcionario){
        ResponseFuncionarioDto funcionarioSalvo = new ResponseFuncionarioDto(funcionarioService.atualizar(funcionarioId,
                funcionario));

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioSalvo);
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<?> removeFuncionario(@PathVariable Long funcionarioId){
        funcionarioService.excluir(funcionarioId);

        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso!");
    }
}
