package com.projeto.imoveis.dto.imovel;

import com.projeto.imoveis.dto.pessoa.PessoaRequestDto;
import com.projeto.imoveis.enums.TipoImovel;
import com.projeto.imoveis.models.Imovel;
import com.projeto.imoveis.models.Pessoa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateImovelDto {

    @Size(min = 3, max = 50)
    private String matriculaImovel;

    @NotNull
    private PessoaRequestDto proprietario;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    private double largura;

    private double comprimento;

    private double preco;

    public CreateImovelDto(String matriculaImovel, PessoaRequestDto proprietario, TipoImovel tipoImovel, double largura, double comprimento, double preco) {
        this.matriculaImovel = matriculaImovel;
        this.proprietario = proprietario;
        this.tipoImovel = tipoImovel;
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }

    public Imovel convertToModel(Pessoa proprietario){
        return new Imovel(matriculaImovel, proprietario, tipoImovel, largura, comprimento, preco);
    }

    public CreateImovelDto() {
        super();
    }
}
