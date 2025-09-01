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

import com.github.thalles.backend.model.Imagem;
import com.github.thalles.backend.service.ImagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/imagem")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @GetMapping
    public ResponseEntity<Page<Imagem>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(imagemService.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagem> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(imagemService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Imagem> inserir(@Valid @RequestBody Imagem imagem) {
        return ResponseEntity.ok(imagemService.inserir(imagem));

    }

    @PutMapping
    public ResponseEntity<Imagem> alterar(@Valid @RequestBody Imagem imagem) {
        return ResponseEntity.ok(imagemService.alterar(imagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        imagemService.excluir(id);
        return ResponseEntity.ok("Excluindo");
    }
}
