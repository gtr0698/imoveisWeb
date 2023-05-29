package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.dto.pessoa.UpdatePessoaDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public Page<Pessoa> listarTodos(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Optional<Pessoa> localizar(Long pessoafId) {
        return pessoaRepository.findById(pessoafId);
    }

    public Pessoa salvar(CreatePessoaDto pessoa) {

        Pessoa pessoaExistente = pessoaRepository.findByEmail(pessoa.getEmail());

        if(pessoaExistente != null){
            throw new RegraException("Já existe uma pessoa com esse email cadastrado.");
        }

        Pessoa novaPessoa = pessoa.convertToModel();
        return pessoaRepository.save(novaPessoa);
    }

    public Pessoa atualizar(Long pessoaId, UpdatePessoaDto pessoaUpdate) {
        Pessoa pessoa = verificaExistencia(pessoaId);
        Pessoa pessoaAtualizada = pessoa.atualizaPessoa(pessoaUpdate.getNome(),
                pessoaUpdate.getEmail(),pessoaUpdate.getTelefone(),pessoaUpdate.getNumeroDocumento(),
                pessoaUpdate.getTipoPessoa(), pessoaUpdate.getPapel(), pessoaUpdate.getSenha());

        return pessoaRepository.save(pessoaAtualizada);
    }

    public Pessoa verificaExistencia(Long pessoaId){
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

        if(pessoa.isEmpty()){

            throw new RegraException("Pessoa não encontrado");
        }

        return pessoa.get();
    }

    public void excluir(Long pessoaId) {

        verificaExistencia(pessoaId);

        pessoaRepository.deleteById(pessoaId);
    }
}
