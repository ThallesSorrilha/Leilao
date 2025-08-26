package com.github.thalles.backend.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "leilao")
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.titulo.notblank}")
    private String titulo;

    @NotBlank(message = "{validation.descricao.notblank}")
    private String descricao;

    @NotBlank(message = "{validation.descricaoDetalhada.notblank}")
    private String descricaoDetalhada;

    @NotBlank(message = "{validation.dataHoraInicio.notblank}")
    private DateTimeFormat dataHoraInicio;

    @NotBlank(message = "{validation.dataHoraFim.notblank}")
    private DateTimeFormat dataHoraFim;

    @NotBlank(message = "{validation.status.notblank}")
    private StatusLeilao status;

    @NotBlank(message = "{validation.observacao.notblank}")
    private String observacao;

    @NotBlank(message = "{validation.valorIncremento.notblank}")
    private Float valorIncremento;

    @NotBlank(message = "{validation.lanceMinimo.notblank}")
    private Float lanceMinimo;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
