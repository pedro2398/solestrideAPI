package com.solestride.controller;

import com.solestride.model.Requisicao;
import com.solestride.service.RequisicaoService;
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
@RequestMapping("requisicao")
public class RequisicaoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RequisicaoService service;

    @GetMapping
    public List<Requisicao> get(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        log.info("Exibindo todos as requisições!");
        return service.get(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Requisicao> getById(@PathVariable Long id){
        log.info("Exibindo requisição de id: " + id);
        return ResponseEntity.ok(service.getByID(id));
    }

    @PostMapping
    public ResponseEntity<Requisicao> post(@RequestBody @Valid Requisicao entity) {
        log.info("Cadastrando requisição");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.post(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Requisicao> put(@PathVariable Long id, @RequestBody @Valid Requisicao entity) {
        log.info("Alterando requisição com id: " + id);
        return ResponseEntity.ok(service.put(id, entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando requisição com id: " + id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
