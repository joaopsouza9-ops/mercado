package com.example.aula6_mercado_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula6_mercado_spring.dto.ProdutosRequestDTO;
import com.example.aula6_mercado_spring.dto.ProdutosResponseDTO;
import com.example.aula6_mercado_spring.service.ProdutosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastro")
@CrossOrigin(origins = "*")
public class ProdutosController {
    @Autowired
    private ProdutosService produtosService;
    
    @GetMapping
    public ResponseEntity<List<ProdutosResponseDTO>> listar(){
        return ResponseEntity
        .ok()
        .body(produtosService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ProdutosRequestDTO dto){
        produtosService.salvarProduto(dto);
        return ResponseEntity
            .created(null)
            .body(Map.of(
                "mensagem", "Cadastrado com sucesso",
                "sucesso", true
            ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutosRequestDTO dto){
        produtosService.atualizarProduto(id, dto);
        return ResponseEntity
            .ok()
            .body(Map.of(
                "mensagem", "Produto atualizado com sucesso",
                "sucesso", true
            ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id){
        produtosService.deletarProduto(id);
        return ResponseEntity
            .ok()
            .body(Map.of(
                "mensagem", "Produto excluído com sucesso",
                "sucesso", true
            ));
    }
}
