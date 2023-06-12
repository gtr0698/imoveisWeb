package com.projeto.imoveis.dto.imovel;

import com.projeto.imoveis.enums.TipoImovel;
import com.projeto.imoveis.models.Pessoa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateImovelDto {

    @Size(min = 3, max = 50)
    private String matriculaImovel;

    @ManyToOne
    private Pessoa proprietario;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @NotBlank
    private double largura;

    @NotBlank
    private double comprimento;

    @NotBlank
    private double preco;


    public UpdateImovelDto(String matriculaImovel, Pessoa proprietario, TipoImovel tipoImovel, double largura, double comprimento, double preco) {
        this.matriculaImovel = matriculaImovel;
        this.proprietario = proprietario;
        this.tipoImovel = tipoImovel;
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }

    public UpdateImovelDto() {
        super();
    }
}
