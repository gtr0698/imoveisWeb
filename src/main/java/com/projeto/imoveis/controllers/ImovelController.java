package com.projeto.imoveis.controllers;

import com.projeto.imoveis.dto.imovel.CreateImovelDto;
import com.projeto.imoveis.dto.imovel.ResponseImovelDto;
import com.projeto.imoveis.dto.imovel.UpdateImovelDto;
import com.projeto.imoveis.models.Imovel;
import com.projeto.imoveis.services.ImovelService;
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
@RequestMapping("cadastros/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    // METODO DE LISTAR TODOS OS IMOVEIS
    @GetMapping
    public ResponseEntity<Page<Imovel>> listaImoveis(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pegeable){
        Page<Imovel> imovel = imovelService.listarTodos(pegeable);
        if(imovel.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(imovel);
    }

    @GetMapping("/{imovelId}")
    public ResponseEntity<Object> buscarImovel(@PathVariable(value = "imovelId") Long imovelId){
        Optional<Imovel> buscarImovel = imovelService.localizar(imovelId);
        if(!buscarImovel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de Imovél não localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarImovel);
    }

    @PostMapping
    public ResponseEntity<ResponseImovelDto> adicionarImovel(@Valid @RequestBody CreateImovelDto imovel){
        ResponseImovelDto imovelSalva = new ResponseImovelDto(imovelService.salvarImovel(imovel));
        return ResponseEntity.status(HttpStatus.CREATED).body(imovelSalva);
    }

    @PutMapping("/{imovelId}")
    public ResponseEntity<ResponseImovelDto> atualizarImovel(@Valid @PathVariable Long imovelId,
                                                             @RequestBody UpdateImovelDto imovel){
        ResponseImovelDto imovelSalva = new ResponseImovelDto(imovelService.atualizar(imovelId, imovel));
        return ResponseEntity.status(HttpStatus.OK).body(imovelSalva);
    }

    @DeleteMapping("/{imovelId}")
    public ResponseEntity<?> removerImovel(@PathVariable Long imovelId){
        imovelService.excluir(imovelId);
        return ResponseEntity.status(HttpStatus.OK).body("Imovel foi removido com Sucesso!!");
    }
}
