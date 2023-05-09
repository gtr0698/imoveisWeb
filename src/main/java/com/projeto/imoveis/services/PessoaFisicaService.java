package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.pessoaF.CreatePessoaFisicaDto;
import com.projeto.imoveis.dto.pessoaF.UpdatePessoaFisicaDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.PessoaFisica;
import com.projeto.imoveis.repositories.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaFisicaService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;


    public Page<PessoaFisica> listarTodos(Pageable pageable) {
        return pessoaFisicaRepository.findAll(pageable);
    }

    public Optional<PessoaFisica> localizar(Long pessoafId) {
        return pessoaFisicaRepository.findById(pessoafId);
    }

    public PessoaFisica salvarPF(CreatePessoaFisicaDto pessoaF) {

        PessoaFisica novaPessoaF = pessoaF.convertToModel();

        return pessoaFisicaRepository.save(novaPessoaF);
    }

    public PessoaFisica atualizar(Long pessoafId, UpdatePessoaFisicaDto pessoaF) {
        PessoaFisica pessoa = verificaExistencia(pessoafId);
        PessoaFisica pessoafAtualizada = pessoa.atualizaPessoaF(pessoaF.getNome(),
                pessoaF.getEmail(),pessoaF.getCpf(),pessoaF.getGenero(), pessoaF.getDataNascimento(), pessoaF.getPapel());

        return pessoaFisicaRepository.save(pessoafAtualizada);
    }

    public PessoaFisica verificaExistencia(Long pessoafId){
        Optional<PessoaFisica> pessoaF = pessoaFisicaRepository.findById(pessoafId);

        if(pessoaF.isEmpty()){

            throw new RegraException("Pessoa não encontrado");
        }

        return pessoaF.get();
    }

    public void excluir(Long pessoafId) {

        verificaExistencia(pessoafId);

        pessoaFisicaRepository.deleteById(pessoafId);
    }
}
