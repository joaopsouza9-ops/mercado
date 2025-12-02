package com.example.aula6_mercado_spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aula6_mercado_spring.model.ProdutosModel;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosModel, Long>{
    Optional<ProdutosModel> findByNome(String nome);
}
