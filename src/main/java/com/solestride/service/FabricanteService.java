package com.solestride.service;

import com.solestride.model.dto.PessoaDto;
import com.solestride.model.pessoa.Fabricante;
import com.solestride.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabricanteService {

    @Autowired
    FabricanteRepository repository;

    public PessoaDto getByID(Long id) {
        Fabricante entity = repository.findById(id).orElseThrow( () -> {
            return new RuntimeException("Não foi possivel fazer a requisição!");
        });

        return new PessoaDto(entity);
    }

    public List<PessoaDto> get(Pageable pageable) {
        List<Fabricante> entities = repository.findAll(pageable).getContent();
        List<PessoaDto> entitiesDto = new ArrayList<>();
        for(Fabricante entity: entities){
            PessoaDto newEntity = new PessoaDto(entity);
            entitiesDto.add(newEntity);
        }
        return entitiesDto;
    }

    public void delete(Long id) {
        getByID(id);
        repository.deleteById(id);
    }

    public PessoaDto post(Fabricante fabricante) {
        repository.save(fabricante);
        return new PessoaDto(fabricante);
    }

    public PessoaDto put(Long id, Fabricante fabricante) {
        getByID(id);
        fabricante.setId(id);
        repository.save(fabricante);
        return new PessoaDto(fabricante);
    }
}
