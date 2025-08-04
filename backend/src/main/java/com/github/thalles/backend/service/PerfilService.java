package com.github.thalles.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thalles.backend.exception.NaoEncontradoExcecao;
import com.github.thalles.backend.model.Perfil;
import com.github.thalles.backend.repository.PerfilRepository;

@Service
public class PerfilService {
    
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private MessageSource messageSource;

    public Perfil inserir(Perfil perfil) {
        Perfil perfilCadastrada = perfilRepository.save(perfil);
        return perfilCadastrada;
    }

    public Perfil alterar(Perfil perfil) {
        Perfil perfilBanco = buscarPorId(perfil.getId());
        perfilBanco.setNome(perfil.getNome());
        return perfilRepository.save(perfilBanco);
    }

    public void excluir(Long id) {
        Perfil perfilBanco = buscarPorId(id);
        perfilRepository.delete(perfilBanco);
    }

    public Page<Perfil> buscarTodos(Pageable pageable) {
        return perfilRepository.findAll(pageable);
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepository.findById(id)
        .orElseThrow(() -> new NaoEncontradoExcecao(messageSource
        .getMessage("perfil.notfound",
        new Object[] {id},
        LocaleContextHolder.getLocale() )));
    }
}
