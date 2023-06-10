package com.projeto.imoveis.repositories;

import com.projeto.imoveis.models.CadFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadFuncionarioRepository extends JpaRepository<CadFuncionario, Long> {
}
