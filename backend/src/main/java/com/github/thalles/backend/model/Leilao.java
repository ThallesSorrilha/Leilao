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
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "leilao")
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.titulo.notblank}")
    @Size(max = 100, message = "{validation.titulo.size}")
    private String titulo;

    @NotBlank(message = "{validation.descricao.notblank}")
    @Size(max = 1000, message = "{validation.descricao.size}")
    private String descricao;

    @NotBlank(message = "{validation.descricaoDetalhada.notblank}")
    @Size(max = 5000, message = "{validation.descricaoDetalhada.size}")
    private String descricaoDetalhada;

    @NotBlank(message = "{validation.dataHoraInicio.notblank}")
    private DateTimeFormat dataHoraInicio;

    @NotBlank(message = "{validation.dataHoraFim.notblank}")
    private DateTimeFormat dataHoraFim;

    @NotBlank(message = "{validation.status.notblank}")
    private StatusLeilao status;

    @NotBlank(message = "{validation.observacao.notblank}")
    @Size(max = 1000, message = "{validation.observacao.size}")
    private String observacao;

    @NotBlank(message = "{validation.valorIncremento.notblank}")
    @Positive(message = "{validation.valorIncremento.positive}")
    private Float valorIncremento;

    @NotBlank(message = "{validation.lanceMinimo.notblank}")
    @Positive(message = "{validation.lanceMinimo.positive}")
    private Float lanceMinimo;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
