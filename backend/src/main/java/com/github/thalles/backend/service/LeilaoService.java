package com.github.thalles.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thalles.backend.exception.NaoEncontradoExcecao;
import com.github.thalles.backend.model.Leilao;
import com.github.thalles.backend.repository.LeilaoRepository;

@Service
public class LeilaoService {

    @Autowired
    private LeilaoRepository leilaoRepository;

    @Autowired
    private MessageSource messageSource;

    public Leilao inserir(Leilao leilao) {
        Leilao leilaoCadastrado = leilaoRepository.save(leilao);
        return leilaoCadastrado;
    }

    public Leilao alterar(Leilao leilao) {
        Leilao leilaoBanco = buscarPorId(leilao.getId());
        leilaoBanco.setTitulo(leilao.getTitulo());
        leilaoBanco.setDescricao(leilao.getDescricao());
        leilaoBanco.setDescricaoDetalhada(leilao.getDescricaoDetalhada());
        leilaoBanco.setDataHoraInicio(leilao.getDataHoraInicio());
        leilaoBanco.setDataHoraFim(leilao.getDataHoraFim());
        leilaoBanco.setStatus(leilao.getStatus());
        leilaoBanco.setObservacao(leilao.getObservacao());
        leilaoBanco.setValorIncremento(leilao.getValorIncremento());
        leilaoBanco.setLanceMinimo(leilao.getLanceMinimo());
        return leilaoRepository.save(leilaoBanco);
    }

    public void excluir(Long id) {
        Leilao leilaoBanco = buscarPorId(id);
        leilaoRepository.delete(leilaoBanco);
    }

    public Page<Leilao> buscarTodos(Pageable pageable) {
        return leilaoRepository.findAll(pageable);
    }

    public Leilao buscarPorId(Long id) {
        return leilaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource
                .getMessage("leilao.notfound",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

}
