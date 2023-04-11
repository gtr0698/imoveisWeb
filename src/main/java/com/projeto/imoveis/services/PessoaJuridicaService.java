package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.pessoaJ.CreatePessoaJuridicaDto;
import com.projeto.imoveis.dto.pessoaJ.UpdatePessoaJuridicaDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.PessoaJuridica;
import com.projeto.imoveis.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaJuridicaService {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    public Page<PessoaJuridica> listarTodos(Pageable pageable) {
        return pessoaJuridicaRepository.findAll(pageable);
    }

    public Optional<PessoaJuridica> localizar(Long pessoajId) {
        return pessoaJuridicaRepository.findById(pessoajId);
    }

    public PessoaJuridica salvarPJ(CreatePessoaJuridicaDto pessoaJ) {

        PessoaJuridica novaPessoaJ = pessoaJ.convertToModel();

        return pessoaJuridicaRepository.save(novaPessoaJ);
    }

    public PessoaJuridica atualizar(Long pessoajId, UpdatePessoaJuridicaDto pessoaJ) {
        PessoaJuridica pessoa = verificaExistencia(pessoajId);
        PessoaJuridica pessoajAtualizada = pessoa.atualizaPessoaJ(pessoaJ.getNome(),
                pessoaJ.getEmail(),pessoaJ.getRazaoSocial(),pessoaJ.getNomeFantasia(), pessoaJ.getCnpj(), pessoaJ.getPapel(), pessoaJ.getSenha());

        return pessoaJuridicaRepository.save(pessoajAtualizada);
    }

    public PessoaJuridica verificaExistencia(Long pessoajId){
        Optional<PessoaJuridica> pessoaJ = pessoaJuridicaRepository.findById(pessoajId);

        if(pessoaJ.isEmpty()){

            throw new RegraException("Pessoa não encontrada");
        }

        return pessoaJ.get();
    }

    public void excluir(Long pessoajId) {

        verificaExistencia(pessoajId);

        pessoaJuridicaRepository.deleteById(pessoajId);
    }
}
