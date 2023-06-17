package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.Login;
import com.projeto.imoveis.dto.pessoa.CreatePessoaDto;
import com.projeto.imoveis.dto.pessoa.UpdatePessoaDto;
import com.projeto.imoveis.enums.TipoPessoa;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.repositories.PessoaRepository;
import com.projeto.imoveis.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    RoleRepository roleRepository;

//    private BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> localizar(Long pessoafId) {
        return pessoaRepository.findById(pessoafId);
    }

    public Pessoa salvar(CreatePessoaDto pessoa) {

        Pessoa pessoaExistente = pessoaRepository.findByEmail(pessoa.getEmail());

        if(pessoaExistente != null){
            throw new RegraException("Já existe um registro com esse email cadastrado.");
        }

        if(pessoa.getNumeroDocumento().length() == 11){
            pessoa.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
        } else if(pessoa.getNumeroDocumento().length() == 14){
            pessoa.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
        } else {
            throw new RegraException("Quantidade de caracteres invalida para o campo Numero Documento.");
        }

        //pessoa.setSenha(passwordEncoder().encode(pessoa.getSenha()));

        Pessoa novaPessoa = pessoa.convertToModel();
        return pessoaRepository.save(novaPessoa);
    }


    public Pessoa atualizar(Long pessoaId, UpdatePessoaDto pessoaUpdate) {
        Pessoa pessoa = verificaExistencia(pessoaId);

        //pessoaUpdate.setSenha(passwordEncoder().encode(pessoaUpdate.getSenha()));

        Pessoa pessoaAtualizada = pessoa.atualizaPessoa(pessoaUpdate.getNome(),
                pessoaUpdate.getEmail(),pessoaUpdate.getTelefone(),pessoaUpdate.getNumeroDocumento(),
                pessoaUpdate.getTipoPessoa(), pessoaUpdate.getSenha());

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

    public Pessoa localizarLogin(Login login){

        //login.setSenha(passwordEncoder().encode(login.getSenha()));

        Pessoa retornaPessoa = pessoaRepository.findByEmail(login.getEmail());

        if(retornaPessoa != null){
            boolean senhasIguais = retornaPessoa.getSenha().compareTo(login.getSenha()) == 0;

            if (senhasIguais) {
                return retornaPessoa;
            }
        }

        return null;
    }
}
