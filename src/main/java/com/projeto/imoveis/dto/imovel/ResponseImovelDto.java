package com.projeto.imoveis.dto.imovel;

import com.projeto.imoveis.enums.TipoImovel;
import com.projeto.imoveis.models.Imovel;
import com.projeto.imoveis.models.Pessoa;
import lombok.Getter;

@Getter
public class ResponseImovelDto {

    private Long idImovel;
    private String matriculaImovel;
    private Pessoa proprietario;
    private TipoImovel tipoImovel;
    private double largura;
    private double comprimento;
    private double preco;

    public ResponseImovelDto(Imovel imovel) {
        this.idImovel = imovel.getId();
        this.matriculaImovel = imovel.getMatriculaImovel();
        this.proprietario = imovel.getProprietario();
        this.tipoImovel = imovel.getTipoImovel();
        this.largura = imovel.getLargura();
        this.comprimento = imovel.getComprimento();
        this.preco = imovel.getPreco();
    }

    public ResponseImovelDto() {
        super();
    }
}
