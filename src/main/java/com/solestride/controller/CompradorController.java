package com.solestride.controller;

import com.solestride.model.dto.PessoaDto;
import com.solestride.model.pessoa.Comprador;
import com.solestride.service.CompradorService;
import jakarta.validation.Valid;
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
@RequestMapping("comprador")
public class CompradorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CompradorService service;

    @GetMapping
    public List<PessoaDto> get(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        log.info("Exibindo todos os compradores!");
        return service.get(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<PessoaDto> getById(@PathVariable Long id){
        log.info("Exibindo comprador de id: " + id);
        return ResponseEntity.ok(service.getByID(id));
    }

    @PostMapping
    public ResponseEntity<PessoaDto> post(@RequestBody @Valid Comprador entity) {
        log.info("Cadastrando comprador");
        PessoaDto newEntity = service.post(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<PessoaDto> put(@PathVariable Long id, @RequestBody @Valid Comprador entity) {
        log.info("Alterando comprador com id: " + id);
        return ResponseEntity.ok(service.put(id, entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando comprador com id: " + id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
