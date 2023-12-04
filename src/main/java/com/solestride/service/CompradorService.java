package com.solestride.service;

import com.solestride.model.dto.PessoaDto;
import com.solestride.model.pessoa.Comprador;
import com.solestride.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompradorService {

    @Autowired
    CompradorRepository repository;

    public PessoaDto getByID(Long id) {
        Comprador entity = repository.findById(id).orElseThrow( () -> {
            return new RuntimeException("Não foi possivel fazer a requisição!");
        });

        return new PessoaDto(entity);
    }

    public List<PessoaDto> get(Pageable pageable) {
        List<Comprador> entities = repository.findAll(pageable).getContent();
        List<PessoaDto> entitiesDto = new ArrayList<>();
        for(Comprador entity: entities){
            PessoaDto newEntity = new PessoaDto(entity);
            entitiesDto.add(newEntity);
        }
        return entitiesDto;
    }

    public void delete(Long id) {
        getByID(id);
        repository.deleteById(id);
    }

    public PessoaDto post(Comprador comprador) {
        repository.save(comprador);
        return new PessoaDto(comprador);
    }

    public PessoaDto put(Long id, Comprador comprador) {
        getByID(id);
        comprador.setId(id);
        repository.save(comprador);
        return new PessoaDto(comprador);
    }
}
