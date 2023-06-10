package com.projeto.imoveis.dto.imovel;

import com.projeto.imoveis.enums.TipoImovel;
import com.projeto.imoveis.models.Pessoa;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateImovelDto {

    @Size(min = 3, max = 50)
    private String matriculaImovel;

    private Pessoa proprietario;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @NotBlank
    private double largura;

    @NotBlank
    private double comprimento;

    @NotBlank
    private double preco;

    public UpdateImovelDto() {
        super();
    }
}
