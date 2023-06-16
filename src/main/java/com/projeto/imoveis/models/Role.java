package com.projeto.imoveis.models;

import com.projeto.imoveis.enums.RoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName nome;

    public Role(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String getAuthority() {
        return this.nome.toString();
    }
}
