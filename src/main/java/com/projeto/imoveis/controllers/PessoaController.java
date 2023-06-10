package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.dto.pessoa.ResponsePessoaDto;
import com.projeto.imoveis.dto.pessoa.UpdatePessoaDto;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.services.PessoaService;
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
@RequestMapping("/cadastros/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {


    @Autowired
    PessoaService pessoaService;


    @GetMapping
    public ResponseEntity<Page<Pessoa>> listaPessoas(@PageableDefault(page = 0, size = 10, sort = "idPessoa", direction = Sort.Direction.ASC)
                                                                    Pageable pageable){
        Page<Pessoa> pessoa = pessoaService.listarTodos(pageable);
        if(pessoa.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Object> buscarPessoa(@PathVariable(value = "pessoaId") Long pessoaId){
        Optional<Pessoa> buscarPessoas = pessoaService.localizar(pessoaId);
        if(!buscarPessoas.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("registro não localizado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarPessoas);
    }

    @PostMapping
    public ResponseEntity<ResponsePessoaDto> adicionarPessoa(@Valid @RequestBody CreatePessoaDto pessoa){
        ResponsePessoaDto pessoaSalva = new ResponsePessoaDto(pessoaService.salvar(pessoa));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<ResponsePessoaDto> atualizarPessoa(@Valid @PathVariable Long pessoaId,
                                                             @RequestBody UpdatePessoaDto pessoa){
        ResponsePessoaDto pessoaSalva = new ResponsePessoaDto(pessoaService.atualizar(pessoaId,
                pessoa));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaSalva);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<?> removerPessoa(@PathVariable Long pessoaId){
        pessoaService.excluir(pessoaId);

        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso!");
    }
}
