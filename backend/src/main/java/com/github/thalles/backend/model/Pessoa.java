package com.github.thalles.backend.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "pessoa")
public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.name.notblank}")
    @Size(max = 100, message = "{validation.name.size}")
    private String nome;

    @NotBlank(message = "{validation.email.notblank}")
    @Email(message = "{validation.email.notvalid}")
    @Size(max = 100, message = "{validation.email.size}")
    private String email;

    @JsonIgnore
    private String senha;

    @NotBlank(message = "{validation.codigoValidacao.notblank}")
    private String codigoValidacao;

    @NotNull(message = "{validation.validateCodigoValidacao.notnull}")
    private Date validateCodigoValidacao;

    @NotNull(message = "{validation.ativo.notnull}")
    private Boolean ativo;

    @JsonIgnore
    @Lob
    private byte[] fotoPerfil;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PessoaPerfil> pessoaPerfil;

    public void setPessoaPerfil(List<PessoaPerfil> pessoaPerfil) {
        for (PessoaPerfil p : pessoaPerfil) {
            p.setPessoa(this);
        }
        this.pessoaPerfil = pessoaPerfil;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return pessoaPerfil.stream().map(user -> new SimpleGrantedAuthority(user.getPerfil().getTipo().toString())) // adicionei .toString()
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
