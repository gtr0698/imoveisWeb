package com.projeto.imoveis.models;

import com.projeto.imoveis.enums.TipoImovel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matriculaImovel;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa proprietario;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;
    private double largura;
    private double comprimento;
    private double preco;

    public Imovel(String matriculaImovel, Pessoa proprietario, TipoImovel tipoImovel, double largura, double comprimento, double preco) {
        this.matriculaImovel = matriculaImovel;
        this.proprietario = proprietario;
        this.tipoImovel = tipoImovel;
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
    }

    public Imovel() {
        super();
    }

    public Imovel atualizaImovel(String matriculaImovel, Pessoa proprietario, TipoImovel tipoImovel, double largura, double comprimento, double preco) {
        this.matriculaImovel = matriculaImovel;
        this.proprietario = proprietario;
        this.tipoImovel = tipoImovel;
        this.largura = largura;
        this.comprimento = comprimento;
        this.preco = preco;
        return this;
    }
}
