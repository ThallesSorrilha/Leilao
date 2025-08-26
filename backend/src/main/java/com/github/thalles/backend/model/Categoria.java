package com.github.thalles.backend.model;

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
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.nome.notblank}")
    private String nome;

    @NotBlank(message = "{validation.observacao.notblank}")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_leilao")
    @Size(max = 100, message = "{validation.leilao.size}")
    private Leilao leilao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @Size(max = 1000, message = "{validation.leilao.size}")
    private Pessoa pessoa;
}
