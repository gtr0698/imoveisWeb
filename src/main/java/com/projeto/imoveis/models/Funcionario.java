package com.projeto.imoveis.models;

import com.projeto.imoveis.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;
    private String nome;

    private String email;

    private String telefone;

    @Size(min = 11, max = 14)
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;


    @ManyToMany
    @JoinTable(name = "funcionario_roles",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected List<Role> role;

    private String senha;

    private String cargo;

    public Funcionario(String nome, String email, String telefone, String numeroDocumento,
                       TipoPessoa tipoPessoa, List<Role> role, String senha, String cargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.role = role;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Funcionario atualizaFuncionario(String nome, String email, String telefone, String numeroDocumento,
                                 TipoPessoa tipoPessoa,String senha, String cargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.senha = senha;
        this.cargo = cargo;
        return this;
    }

}
