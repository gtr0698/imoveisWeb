package com.projeto.imoveis.models;

import com.projeto.imoveis.enums.TipoPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    protected String nome;

    protected String email;

    protected String telefone;

    @Size(min = 11, max = 14)
    protected String numeroDocumento;

    @Enumerated(EnumType.STRING)
    protected TipoPessoa tipoPessoa;

    @ManyToMany
    @JoinTable(name = "pessoas_roles",
    joinColumns = @JoinColumn(name = "id_pessoa"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected List<Role> role;

    protected String senha;

    protected String cargo;

    public Pessoa(String nome, String email, String telefone, String numeroDocumento, TipoPessoa tipoPessoa,
                  List<Role> role, String senha, String cargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.numeroDocumento = numeroDocumento;
        this.tipoPessoa = tipoPessoa;
        this.role = role;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Pessoa() {
        super();
    }

    public Pessoa atualizaPessoa(String nome, String email, String telefone, String numeroDocumento,
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
