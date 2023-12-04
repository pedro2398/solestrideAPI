package com.solestride.controller;

import com.solestride.model.dto.PessoaDto;
import com.solestride.model.pessoa.Fornecedor;
import com.solestride.service.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FornecedorService service;

    @GetMapping
    public List<PessoaDto> get(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        log.info("Exibindo todos os fornecedores!");
        return service.get(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaDto> getById(@PathVariable Long id){
        log.info("Exibindo fornecedor de id: " + id);
        return ResponseEntity.ok(service.getByID(id));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> post(@RequestBody Fornecedor entity) {
        log.info("Cadastrando fornecedor");
        PessoaDto newEntity = service.post(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaDto> put(@PathVariable Long id, @RequestBody Fornecedor entity) {
        log.info("Alterando fornecedor com id: " + id);
        return ResponseEntity.ok(service.put(id, entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando fornecedor com id: " + id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
