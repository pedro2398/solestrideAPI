package com.solestride.service;

import com.solestride.model.Requisicao;
import com.solestride.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequisicaoService {

    @Autowired
    RequisicaoRepository repository;

    public Requisicao getByID(Long id) {
        Requisicao entity = repository.findById(id).orElseThrow( () -> {
            return new RuntimeException("Não foi possivel fazer a requisição!");
        });

        return entity;
    }

    public Page<Requisicao> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void delete(Long id) {
        getByID(id);
        repository.deleteById(id);
    }

    public Requisicao post(Requisicao requisicao) {
        repository.save(requisicao);
        return requisicao;
    }

    public Requisicao put(Long id, Requisicao requisicao) {
        getByID(id);
        requisicao.setId(id);
        repository.save(requisicao);
        return requisicao;
    }
}
