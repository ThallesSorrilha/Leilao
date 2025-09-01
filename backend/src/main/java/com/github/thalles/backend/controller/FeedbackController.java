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

import com.github.thalles.backend.model.Feedback;
import com.github.thalles.backend.service.FeedbackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<Page<Feedback>> buscarTodos(Pageable pageable) {
        return ResponseEntity.ok(feedbackService.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(feedbackService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Feedback> inserir(@Valid @RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.inserir(feedback));
    }

    @PutMapping
    public ResponseEntity<Feedback> alterar(@Valid @RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.alterar(feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Long id) {
        feedbackService.excluir(id);
        return ResponseEntity.ok("Excluindo");
    }
}
