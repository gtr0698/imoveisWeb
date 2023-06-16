package com.projeto.imoveis.repositories;

import com.projeto.imoveis.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByEmail(String email);
}
