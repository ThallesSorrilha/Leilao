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
import lombok.Data;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.valor.notblank}")
    @Positive(message = "{validation.valor.positive}")
    private Float valor;

    @NotBlank(message = "{validation.dataHora.notblank}")
    private DateTimeFormat dataHora;

    @NotBlank(message = "{validation.status.notblank}")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    @NotBlank(message = "{validation.leilao.notblank}")
    private Leilao leilao;
}
