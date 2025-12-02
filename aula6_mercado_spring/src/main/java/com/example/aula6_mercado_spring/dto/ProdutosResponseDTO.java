package com.example.aula6_mercado_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutosResponseDTO {
    private String nome;
    private Double preco;
}
