package com.github.thalles.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thalles.backend.exception.NaoEncontradoExcecao;
import com.github.thalles.backend.model.Imagem;
import com.github.thalles.backend.repository.ImagemRepository;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private MessageSource messageSource;

    public Imagem inserir(Imagem imagem) {
        Imagem imagemCadastrado = imagemRepository.save(imagem);
        return imagemCadastrado;
    }

    public Imagem alterar(Imagem imagem) {
        Imagem imagemBanco = buscarPorId(imagem.getId());
        imagemBanco.setDataHoraCadastro(imagem.getDataHoraCadastro());
        imagemBanco.setNomeImagem(imagem.getNomeImagem());
        return imagemRepository.save(imagemBanco);
    }

    public void excluir(Long id) {
        Imagem imagemBanco = buscarPorId(id);
        imagemRepository.delete(imagemBanco);
    }

    public Page<Imagem> buscarTodos(Pageable pageable) {
        return imagemRepository.findAll(pageable);
    }

    public Imagem buscarPorId(Long id) {
        return imagemRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource
                .getMessage("imagem.notfound",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

}
