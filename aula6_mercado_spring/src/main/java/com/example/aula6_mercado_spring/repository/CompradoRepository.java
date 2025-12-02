package com.example.aula6_mercado_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aula6_mercado_spring.model.CompradoModel;

@Repository
public interface CompradoRepository extends JpaRepository<CompradoModel, Long>{

}
