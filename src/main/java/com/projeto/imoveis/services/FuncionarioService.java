package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.Login;
import com.projeto.imoveis.dto.funcionario.CreateFuncionarioDto;
import com.projeto.imoveis.dto.funcionario.UpdateFuncionarioDto;
import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.Funcionario;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.repositories.FuncionarioRepository;
import com.projeto.imoveis.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

//    private BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


    public List<Funcionario> listarTodosFunc() {
        return funcionarioRepository.findAll();
    }


    public Optional<Funcionario> localizarFunc(Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId);
    }


    public Funcionario salvarFunc(CreateFuncionarioDto funcionario) {

        Funcionario funcionarioExistente = funcionarioRepository.findByEmail(funcionario.getEmail());

        if(funcionarioExistente != null){
            throw new RegraException("Já existe um registro com esse email cadastrado.");
        }

        //funcionario.setSenha(passwordEncoder().encode(funcionario.getSenha()));

        Funcionario novoFuncionario = funcionario.convertToModel();

        return funcionarioRepository.save(novoFuncionario);
    }


    public Funcionario atualizarFunc(Long funcionarioId, UpdateFuncionarioDto funcionario) {
        Funcionario func = verificaExistencia(funcionarioId);

        //funcionario.setSenha(passwordEncoder().encode(funcionario.getSenha()));

        Funcionario funcAtualizado = func.atualizaFuncionario(funcionario.getNome(), funcionario.getEmail(),
                funcionario.getTelefone(), funcionario.getNumeroDocumento(), funcionario.getTipoPessoa(),
                funcionario.getSenha(), funcionario.getCargo());

        return funcionarioRepository.save(funcAtualizado);
    }

    public void excluirFunc(Long funcionarioId) {

        verificaExistencia(funcionarioId);

        funcionarioRepository.deleteById(funcionarioId);
    }

    public Funcionario verificaExistencia(Long funcionarioId){
        Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioId);

        if(funcionario.isEmpty()){

            throw new RegraException("Funcionario não encontrado");
        }

        return funcionario.get();
    }

    public Funcionario localizarLogin(Login login) {

        Funcionario retornaFuncionario = funcionarioRepository.findByEmail(login.getEmail());

        retornaFuncionario.getSenha().compareTo(login.getSenha());

        return retornaFuncionario;
    }
}
