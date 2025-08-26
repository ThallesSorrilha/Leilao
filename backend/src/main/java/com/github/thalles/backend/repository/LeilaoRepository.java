package com.github.thalles.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thalles.backend.model.Leilao;

public interface LeilaoRepository extends JpaRepository<Leilao, Long> {

}
