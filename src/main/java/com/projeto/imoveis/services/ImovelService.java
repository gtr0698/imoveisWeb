package com.projeto.imoveis.services;

import com.projeto.imoveis.dto.imovel.CreateImovelDto;
import com.projeto.imoveis.dto.imovel.UpdateImovelDto;
import com.projeto.imoveis.exception.RegraException;
import com.projeto.imoveis.models.Imovel;
import com.projeto.imoveis.models.Pessoa;
import com.projeto.imoveis.repositories.ImovelRepository;
import com.projeto.imoveis.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Imovel> listarTodos() {
        return imovelRepository.findAll();
    }

    public Optional<Imovel> localizar(Long imovelId){
        return imovelRepository.findById(imovelId);
    }

    public Imovel salvarImovel(CreateImovelDto imovel) {
        Pessoa proprietario = pessoaRepository.findById(imovel.getProprietario().getIdPessoa()).orElseThrow(() ->
                new RegraException("Proprietario não encontrado com o id = " + imovel.getProprietario().getIdPessoa(), "Proprietario"));

        Imovel novoImovel = imovel.convertToModel(proprietario);

        return imovelRepository.save(novoImovel);
    }

    public Imovel atualizar(Long imovelId, UpdateImovelDto imovel){
        Imovel imov = verificaExistencia(imovelId);
        Pessoa proprietario = verificarProprietarioExistente(imovel.getProprietario().getIdPessoa());
        Imovel imovAtualizada = imov.atualizaImovel(imovel.getMatriculaImovel(),
                proprietario, imovel.getTipoImovel(), imovel.getLargura(), imovel.getComprimento(), imovel.getPreco());
        return imovelRepository.save(imovAtualizada);
    }

    public Imovel verificaExistencia(Long imovelId){
        Optional<Imovel> imovel = imovelRepository.findById(imovelId);
        if(imovel.isEmpty()){
            throw new RegraException("Imovel não encontrado");
        }
        return imovel.get();
    }

    public Pessoa verificarProprietarioExistente(Long pessoaId){
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);
        if (pessoa.isEmpty()){
            throw new RegraException("Proprietario não encontrado");
        }
        return pessoa.get();
    }

    public void excluir(Long imovelId){
        verificaExistencia(imovelId);
        imovelRepository.deleteById(imovelId);
    }
}
