package com.github.thalles.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.thalles.backend.model.Pessoa;

public interface PessoasRepository extends JpaRepository<Pessoa, Long> {
    
}
