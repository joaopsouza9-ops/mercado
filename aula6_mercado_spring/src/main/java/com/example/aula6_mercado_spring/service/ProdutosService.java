package com.example.aula6_mercado_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aula6_mercado_spring.dto.ProdutosRequestDTO;
import com.example.aula6_mercado_spring.dto.ProdutosResponseDTO;
import com.example.aula6_mercado_spring.model.ProdutosModel;
import com.example.aula6_mercado_spring.repository.ProdutosRepository;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository produtosRepository;

    //CONSULTAR TODOS OS Produtos
    public List<ProdutosResponseDTO> listarTodos() {
        return produtosRepository
            .findAll()
            .stream()
            .map(u -> new ProdutosResponseDTO(u.getNome(), u.getPreco()))
            .toList();
    }

    //SALVAR UM PRODUTO
    public ProdutosModel salvarProduto(ProdutosRequestDTO dto){
        if (produtosRepository.findByNome(dto.getNome()).isPresent()) {
            throw new RuntimeException("Produto ja cadastrado");
            
        }
        ProdutosModel novoProdutosModel = new ProdutosModel();
        novoProdutosModel.setNome(dto.getNome());
        novoProdutosModel.setPreco(dto.getPreco());

        produtosRepository.save(novoProdutosModel);
        return novoProdutosModel;
    }

    //ATUALIZAR UM PRODUTO
    public ProdutosModel atualizarProduto(Long id, ProdutosRequestDTO dto){
        if (!produtosRepository.existsById(id)) {
            throw new RuntimeException("Produtos não cadastrados");
            
        }

        ProdutosModel atualizarProdutosModel = new ProdutosModel();
        atualizarProdutosModel.setId(id);
        atualizarProdutosModel.setNome(dto.getNome());
        atualizarProdutosModel.setPreco(dto.getPreco());

        produtosRepository.save(atualizarProdutosModel);
        return atualizarProdutosModel;
    }
    
    //DELETAR UM Produto
    public void deletarProduto(Long id){
        if (!produtosRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
            
        }
        produtosRepository.deleteById(id);
    }
}
