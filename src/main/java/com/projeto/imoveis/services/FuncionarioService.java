package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    PessoaRepository pessoaRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public List<Pessoa> listarTodosFunc() {
        return pessoaRepository.findAllByCargo();
    }

    /*
    public Optional<Pessoa> localizarFunc(Long funcionarioId) {
        return pessoaRepository.findById(funcionarioId);
    }
    */

    public Pessoa salvarFunc(CreatePessoaDto funcionario) {

        Optional<Pessoa> pessoaExistente = pessoaRepository.findByEmail(funcionario.getEmail());

        if(pessoaExistente.isPresent()){
            throw new RegraException("Já existe um registro com esse email cadastrado.");
        }

        funcionario.setSenha(passwordEncoder().encode(funcionario.getSenha()));

        Pessoa novoFuncionario = funcionario.convertToModel();

        return pessoaRepository.save(novoFuncionario);
    }

    /*
    public Pessoa atualizarFunc(Long funcionarioId, UpdatePessoaDto funcionario) {
        Pessoa func = verificaExistencia(funcionarioId);

        funcionario.setSenha(passwordEncoder().encode(funcionario.getSenha()));

        Pessoa funcAtualizado = func.atualizaPessoa(funcionario.getNome(), funcionario.getEmail(),
                funcionario.getTelefone(), funcionario.getNumeroDocumento(), funcionario.getTipoPessoa(),
                funcionario.getSenha(), funcionario.getCargo());

        return pessoaRepository.save(funcAtualizado);
    }

    public void excluirFunc(Long funcionarioId) {

        verificaExistencia(funcionarioId);

        pessoaRepository.deleteById(funcionarioId);
    }

    public Pessoa verificaExistencia(Long funcionarioId){
        Optional<Pessoa> funcionario = pessoaRepository.findById(funcionarioId);

        if(funcionario.isEmpty()){

            throw new RegraException("Funcionario não encontrado");
        }

        return funcionario.get();
    }
    */
}
