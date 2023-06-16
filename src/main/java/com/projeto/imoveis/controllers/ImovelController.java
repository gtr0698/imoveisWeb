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
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO', 'ROLE_CLIENTE')")
    @GetMapping("/listar")
    public ResponseEntity<List<Imovel>> listaImoveis(){
        List<Imovel> imovel = imovelService.listarTodos();
        if(imovel.isEmpty()){
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(imovel);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO', 'ROLE_CLIENTE')")
    @GetMapping("/buscar/{imovelId}")
    public ResponseEntity<Object> buscarImovel(@PathVariable(value = "imovelId") Long imovelId){
        Optional<Imovel> buscarImovel = imovelService.localizar(imovelId);
        if(!buscarImovel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de Imovél não localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(buscarImovel);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("criar")
    public ResponseEntity<ResponseImovelDto> adicionarImovel(@Valid @RequestBody CreateImovelDto imovel){
        ResponseImovelDto imovelSalva = new ResponseImovelDto(imovelService.salvarImovel(imovel));
        return ResponseEntity.status(HttpStatus.CREATED).body(imovelSalva);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @PutMapping("/atualizar/{imovelId}")
    public ResponseEntity<ResponseImovelDto> atualizarImovel(@Valid @PathVariable Long imovelId,
                                                             @RequestBody UpdateImovelDto imovel){
        ResponseImovelDto imovelSalva = new ResponseImovelDto(imovelService.atualizar(imovelId, imovel));
        return ResponseEntity.status(HttpStatus.OK).body(imovelSalva);
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/remover/{imovelId}")
    public ResponseEntity<?> removerImovel(@PathVariable Long imovelId){
        imovelService.excluir(imovelId);
        return ResponseEntity.status(HttpStatus.OK).body("Imovel foi removido com Sucesso!!");
    }
}
