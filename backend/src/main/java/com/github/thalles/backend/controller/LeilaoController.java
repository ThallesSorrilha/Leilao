package com.github.thalles.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.thalles.backend.model.Leilao;
import com.github.thalles.backend.service.LeilaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leilao")
public class LeilaoController {

    @Autowired
    private LeilaoService leilaoService;

    @GetMapping
    public ResponseEntity<Page<Leilao>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(leilaoService.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leilao> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(leilaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Leilao> inserir(@Valid @RequestBody Leilao leilao) {
        return ResponseEntity.ok(leilaoService.inserir(leilao));
    }

    @PutMapping
    public ResponseEntity<Leilao> alterar(@Valid @RequestBody Leilao leilao) {
        return ResponseEntity.ok(leilaoService.alterar(leilao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        leilaoService.excluir(id);
        return ResponseEntity.ok("Excluindo");
    }
}
