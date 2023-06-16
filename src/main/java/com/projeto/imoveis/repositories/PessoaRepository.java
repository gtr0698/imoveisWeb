package com.projeto.imoveis.repositories;

import com.projeto.imoveis.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByEmail(String email);

    @Query("SELECT p FROM Pessoa p WHERE p.cargo IS NOT NULL")
    List<Pessoa> findAllByCargo();

    @Query("SELECT p FROM Pessoa p WHERE p.cargo IS NULL")
    List<Pessoa> findAllClientes();
}
