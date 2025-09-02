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
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{validation.valor.notnull}")
    @Positive(message = "{validation.valor.positive}")
    private Float valor;

    @NotNull(message = "{validation.dataHora.notnull}")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    @NotBlank(message = "{validation.status.notblank}")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    @NotNull(message = "{validation.leilao.notnull}")
    private Leilao leilao;
}
