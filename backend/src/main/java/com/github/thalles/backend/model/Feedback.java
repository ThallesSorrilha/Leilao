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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "pagamento")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1000, message = "{validation.comentario.size}")
    @NotBlank(message = "{validation.comentario.notblank}")
    private String comentario;

    @NotBlank(message = "{validation.nota.notblank}")
    @Size(min = 0, max = 10, message = "{validation.nota.size}")
    private Integer nota;

    @NotBlank(message = "{validation.dataHora.notblank}")
    private DateTimeFormat dataHora;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @NotBlank(message = "{validation.pessoa.notblank}")
    private Pessoa pessoa;
}
