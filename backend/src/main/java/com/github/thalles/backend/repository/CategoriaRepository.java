package com.github.thalles.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thalles.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
