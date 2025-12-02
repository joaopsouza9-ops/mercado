package com.example.aula6_mercado_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aula6_mercado_spring.dto.CarrinhoRequestDTO;
import com.example.aula6_mercado_spring.dto.CarrinhoResponseDTO;
import com.example.aula6_mercado_spring.model.CarrinhoModel;

import com.example.aula6_mercado_spring.repository.CarrinhoRepository;


@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    //CONSULTAR TODOS OS Produtos
    public List<CarrinhoResponseDTO> listarTodos() {
        return carrinhoRepository
            .findAll()
            .stream()
            .map(u -> new CarrinhoResponseDTO(u.getNome(), u.getPreco()))
            .toList();
    }

    //SALVAR UM PRODUTO
    public CarrinhoModel salvarCarrinho(CarrinhoRequestDTO dto){
        CarrinhoModel novoCarrinhoModel = new CarrinhoModel();
        novoCarrinhoModel.setNome(dto.getNome());
        novoCarrinhoModel.setPreco(dto.getPreco());

        carrinhoRepository.save(novoCarrinhoModel);
        return novoCarrinhoModel;
    }

    //ATUALIZAR UM PRODUTO
    public CarrinhoModel atualizarCarrinho(Long id, CarrinhoRequestDTO dto){
        if (!carrinhoRepository.existsById(id)) {
            throw new RuntimeException("Produto não cadastrado");
            
        }

        CarrinhoModel atualizarCarrinhoModel = new CarrinhoModel();
        atualizarCarrinhoModel.setId(id);
        atualizarCarrinhoModel.setNome(dto.getNome());
        atualizarCarrinhoModel.setPreco(dto.getPreco());

        carrinhoRepository.save(atualizarCarrinhoModel);
        return atualizarCarrinhoModel;
    }
    
    //DELETAR UM Produto
    public void deletarCarrinho(Long id){
        if (!carrinhoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
            
        }
        carrinhoRepository.deleteById(id);
    }
}
