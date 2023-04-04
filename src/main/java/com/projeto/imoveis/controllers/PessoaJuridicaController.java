package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.pessoaJ.CreatePessoaJuridicaDto;
import com.projeto.imoveis.dto.pessoaJ.ResponsePessoaJuridicaDto;
import com.projeto.imoveis.dto.pessoaJ.UpdatePessoaJuridicaDto;
import com.projeto.imoveis.models.PessoaJuridica;
import com.projeto.imoveis.services.PessoaJuridicaService;
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
@RequestMapping("/cadastros/pessoa-juridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaService pessoaJuridicaService;

    @GetMapping
    public ResponseEntity<Page<PessoaJuridica>> listaPessoasJuridicas(@PageableDefault(page = 0, size = 10, sort = "idPessoa", direction = Sort.Direction.ASC)
                                                                Pageable pageable){
        Page<PessoaJuridica> pessoaJuridica = pessoaJuridicaService.listarTodos(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(pessoaJuridica);
    }

    @GetMapping("/{pessoajId}")
    public ResponseEntity<Object>buscarPessoaJuridica(@PathVariable(value = "pessoajId") Long pessoajId){
        Optional<PessoaJuridica> buscarPessoasJ = pessoaJuridicaService.localizar(pessoajId);
        if(!buscarPessoasJ.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("registro não localizado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarPessoasJ);
    }

    @PostMapping
    public ResponseEntity<ResponsePessoaJuridicaDto>adicionarPessoaJuridica(@Valid @RequestBody CreatePessoaJuridicaDto pessoaJ){
        ResponsePessoaJuridicaDto pessoaSalva = new ResponsePessoaJuridicaDto(pessoaJuridicaService.salvarPJ(pessoaJ));

        return ResponseEntity.ok(pessoaSalva);
    }

    @PutMapping("/{pessoajId}")
    public ResponseEntity<ResponsePessoaJuridicaDto>atualizarPessoaJuridica(@Valid @PathVariable Long pessoajId,
                                                                        @RequestBody UpdatePessoaJuridicaDto pessoaJ){
        ResponsePessoaJuridicaDto pessoaSalva = new ResponsePessoaJuridicaDto(pessoaJuridicaService.atualizar(pessoajId,
                pessoaJ));

        return ResponseEntity.ok(pessoaSalva);
    }

    @DeleteMapping("/{pessoajId}")
    public ResponseEntity<?> removerPessoaJuridica(@PathVariable Long pessoajId){
        pessoaJuridicaService.excluir(pessoajId);

        return ResponseEntity.noContent().build();
    }
}
