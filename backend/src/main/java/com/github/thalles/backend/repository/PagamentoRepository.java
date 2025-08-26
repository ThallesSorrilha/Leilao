package com.github.thalles.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thalles.backend.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
