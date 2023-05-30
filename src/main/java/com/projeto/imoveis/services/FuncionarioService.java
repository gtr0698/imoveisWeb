package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.funcionario.CreateFuncionarioDto;
import com.projeto.imoveis.dto.funcionario.UpdateFuncionarioDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.CadFuncionario;
import com.projeto.imoveis.repositories.CadFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    CadFuncionarioRepository funcionarioRepository;

    public Page<CadFuncionario> listarTodos(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Optional<CadFuncionario> localizar(Long funcionarioId) {
        return funcionarioRepository.findById(funcionarioId);
    }

    public CadFuncionario salvar(CreateFuncionarioDto funcionario) {

        CadFuncionario novoFuncionario = funcionario.convertToModel();

        return funcionarioRepository.save(novoFuncionario);
    }

    public CadFuncionario atualizar(Long funcionarioId, UpdateFuncionarioDto funcionario) {
        CadFuncionario func = verificaExistencia(funcionarioId);
        CadFuncionario funcionarioAtualizado = func.atualizaCadFuncionario(funcionario.getNome(), funcionario.getEmail(),
                funcionario.getTelefone(), funcionario.getNumeroDocumento(),funcionario.getTipoPessoa(), funcionario.getPapel(),
                funcionario.getCargo(), funcionario.getSenha());

        return funcionarioRepository.save(funcionarioAtualizado);
    }

    public CadFuncionario verificaExistencia(Long funcionarioId){
        Optional<CadFuncionario> funcionario = funcionarioRepository.findById(funcionarioId);

        if(funcionario.isEmpty()){

            throw new RegraException("Funcionario não encontrado");
        }

        return funcionario.get();
    }

    public void excluir(Long funcionarioId) {

        verificaExistencia(funcionarioId);

        funcionarioRepository.deleteById(funcionarioId);
    }
}
