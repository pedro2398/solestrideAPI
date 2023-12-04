package com.solestride.controller;

import com.solestride.model.Produto;
import com.solestride.service.ProdutoService;
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
@RequestMapping("produto")
public class ProdutoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProdutoService service;

    @GetMapping
    public List<Produto> get(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        log.info("Exibindo todos os produtos!");
        return service.get(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        log.info("Exibindo produto de id: " + id);
        return ResponseEntity.ok(service.getByID(id));
    }

    @PostMapping
    public ResponseEntity<Produto> post(@RequestBody @Valid Produto entity) {
        log.info("Cadastrando produto");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.post(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> put(@PathVariable Long id, @RequestBody @Valid Produto entity) {
        log.info("Alterando produto com id: " + id);
        return ResponseEntity.ok(service.put(id, entity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando produto com id: " + id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
