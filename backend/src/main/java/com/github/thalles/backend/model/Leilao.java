package com.github.thalles.backend.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.thalles.backend.enums.StatusLeilao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Size(max = 5000, message = "{validation.descricaoDetalhada.size}")
    private String descricaoDetalhada;

    @NotNull(message = "{validation.dataHoraInicio.notnull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraInicio;

    @NotNull(message = "{validation.dataHoraFim.notnull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{validation.status.notnull}")
    private StatusLeilao status;

    @Size(max = 1000, message = "{validation.observacao.size}")
    private String observacao;

    @NotNull(message = "{validation.valorIncremento.notnull}")
    @Positive(message = "{validation.valorIncremento.positive}")
    private Float valorIncremento;

    @NotNull(message = "{validation.lanceMinimo.notnull}")
    @Positive(message = "{validation.lanceMinimo.positive}")
    private Float lanceMinimo;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
