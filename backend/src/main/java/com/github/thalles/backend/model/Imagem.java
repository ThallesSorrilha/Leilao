package com.github.thalles.backend.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
@Table(name = "imagem")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{validation.dataHoraCadastro.notnull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraCadastro;

    @NotBlank(message = "{validation.nomeImagem.notblank}")
    private String nomeImagem;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    private Leilao leilao;
}
