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

import com.example.aula6_mercado_spring.dto.CarrinhoRequestDTO;
import com.example.aula6_mercado_spring.dto.CarrinhoResponseDTO;

import com.example.aula6_mercado_spring.service.CarrinhoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public ResponseEntity<List<CarrinhoResponseDTO>> listar() {
        return ResponseEntity
                .ok()
                .body(carrinhoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody CarrinhoRequestDTO dto) {
        carrinhoService.salvarCarrinho(dto);
        return ResponseEntity
                .created(null)
                .body(Map.of(
                        "mensagem", "Cadastrado no carrinho com sucesso",
                        "sucesso", true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CarrinhoRequestDTO dto) {
        carrinhoService.atualizarCarrinho(id, dto);
        return ResponseEntity
                .ok()
                .body(Map.of(
                        "mensagem", "Produto atualizado com sucesso",
                        "sucesso", true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletar(@PathVariable Long id) {
        carrinhoService.deletarCarrinho(id);
        return ResponseEntity
                .ok()
                .body(Map.of(
                        "mensagem", "Produto excluído do carrinho com sucesso",
                        "sucesso", true));
    }
}
