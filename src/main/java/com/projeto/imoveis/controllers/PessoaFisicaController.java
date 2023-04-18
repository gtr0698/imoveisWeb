package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.pessoaF.CreatePessoaFisicaDto;
import com.projeto.imoveis.dto.pessoaF.ResponsePessoaFisicaDto;
import com.projeto.imoveis.dto.pessoaF.UpdatePessoaFisicaDto;
import com.projeto.imoveis.models.PessoaFisica;
import com.projeto.imoveis.services.PessoaFisicaService;
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
@RequestMapping("/cadastros/pessoa-fisica")
public class PessoaFisicaController {


    @Autowired
    PessoaFisicaService pessoaFisicaService;


    @GetMapping
    public ResponseEntity<Page<PessoaFisica>> listaPessoasFisicas(@PageableDefault(page = 0, size = 10, sort = "idPessoa", direction = Sort.Direction.ASC)
                                                                    Pageable pageable){
        Page<PessoaFisica> pessoaFisica = pessoaFisicaService.listarTodos(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(pessoaFisica);
    }

    @GetMapping("/{pessoafId}")
    public ResponseEntity<Object>buscarPessoaFisica(@PathVariable(value = "pessoafId") Long pessoafId){
        Optional<PessoaFisica> buscarPessoasF = pessoaFisicaService.localizar(pessoafId);
        if(!buscarPessoasF.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("registro não localizado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarPessoasF);
    }

    @PostMapping
    public ResponseEntity<ResponsePessoaFisicaDto>adicionarPessoaFisica(@Valid @RequestBody CreatePessoaFisicaDto pessoaF){
        ResponsePessoaFisicaDto pessoaSalva = new ResponsePessoaFisicaDto(pessoaFisicaService.salvarPF(pessoaF));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PutMapping("/{pessoafId}")
    public ResponseEntity<ResponsePessoaFisicaDto>atualizarPessoaFisica(@Valid @PathVariable Long pessoafId,
                                                       @RequestBody UpdatePessoaFisicaDto pessoaF){
        ResponsePessoaFisicaDto pessoaSalva = new ResponsePessoaFisicaDto(pessoaFisicaService.atualizar(pessoafId,
                pessoaF));

        return ResponseEntity.status(HttpStatus.OK).body(pessoaSalva);
    }

    @DeleteMapping("/{pessoafId}")
    public ResponseEntity<?> removerPessoaFisica(@PathVariable Long pessoafId){
        pessoaFisicaService.excluir(pessoafId);

        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso!");
    }
}
