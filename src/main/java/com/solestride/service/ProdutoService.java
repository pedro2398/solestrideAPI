package com.solestride.service;

import com.solestride.model.Produto;
import com.solestride.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public Produto getByID(Long id) {
        Produto entity = repository.findById(id).orElseThrow( () -> {
            return new RuntimeException("Não foi possivel fazer a requisição!");
        });

        return entity;
    }

    public Page<Produto> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void delete(Long id) {
        getByID(id);
        repository.deleteById(id);
    }

    public Produto post(Produto produto) {
        repository.save(produto);
        return produto;
    }

    public Produto put(Long id, Produto produto) {
        getByID(id);
        produto.setId(id);
        repository.save(produto);
        return produto;
    }
}
