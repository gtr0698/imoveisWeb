package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.Login;
import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.dto.pessoa.ResponsePessoaDto;
import com.projeto.imoveis.dto.pessoa.UpdatePessoaDto;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {


    @Autowired
    PessoaService pessoaService;


    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("/listar")
    public ResponseEntity<List<ResponsePessoaDto>> listaPessoas(){
        List<ResponsePessoaDto> pessoas = pessoaService.listarTodos().stream()
                .map(pessoa -> new ResponsePessoaDto(pessoa)).collect(Collectors.toList());
        if(pessoas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("/buscar/{pessoaId}")
    public ResponseEntity<Object> buscarPessoa(@PathVariable(value = "pessoaId") Long pessoaId){
        Optional<Pessoa> buscarPessoas = pessoaService.localizar(pessoaId);
        if(!buscarPessoas.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("registro não localizado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarPessoas);
    }

    //@PreAuthorize("permitAll()")
    @PostMapping("/criar")
    public ResponseEntity<ResponsePessoaDto> adicionarPessoa(@Valid @RequestBody CreatePessoaDto pessoa){
        ResponsePessoaDto pessoaSalva = new ResponsePessoaDto(pessoaService.salvar(pessoa));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENTE')")
    @PutMapping("/atualizar/{pessoaId}")
    public ResponseEntity<ResponsePessoaDto> atualizarPessoa(@Valid @PathVariable Long pessoaId,
                                                             @RequestBody UpdatePessoaDto pessoa){
        ResponsePessoaDto pessoaSalva = new ResponsePessoaDto(pessoaService.atualizar(pessoaId,
                pessoa));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaSalva);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/remover/{pessoaId}")
    public ResponseEntity<?> removerPessoa(@PathVariable Long pessoaId){
        pessoaService.excluir(pessoaId);

        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso!");
    }

    //@PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login login){
        Pessoa buscarPessoaNoLogin = pessoaService.localizarLogin(login);
        if(buscarPessoaNoLogin == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarPessoaNoLogin);
    }

}
