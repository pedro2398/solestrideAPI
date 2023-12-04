package com.solestride.service;

import com.solestride.model.dto.PessoaDto;
import com.solestride.model.pessoa.Fornecedor;
import com.solestride.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository repository;

    public PessoaDto getByID(Long id) {
        Fornecedor entity = repository.findById(id).orElseThrow( () -> {
            return new RuntimeException("Não foi possivel fazer a requisição!");
        });

        return new PessoaDto(entity);
    }

    public List<PessoaDto> get(Pageable pageable) {
        List<Fornecedor> entities = repository.findAll(pageable).getContent();
        List<PessoaDto> entitiesDto = new ArrayList<>();
        for(Fornecedor entity: entities){
            PessoaDto newEntity = new PessoaDto(entity);
            entitiesDto.add(newEntity);
        }
        return entitiesDto;
    }

    public void delete(Long id) {
        getByID(id);
        repository.deleteById(id);
    }

    public Fornecedor post(Fornecedor fornecedor) {
        repository.save(fornecedor);
        return fornecedor;
    }

    public Fornecedor put(Long id, Fornecedor fornecedor) {
        getByID(id);
        fornecedor.setId(id);
        repository.save(fornecedor);
        return fornecedor;
    }
}
