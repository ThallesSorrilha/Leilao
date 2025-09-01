package com.github.thalles.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thalles.backend.exception.NaoEncontradoExcecao;
import com.github.thalles.backend.model.Feedback;
import com.github.thalles.backend.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private MessageSource messageSource;

    public Feedback inserir(Feedback feedback) {
        Feedback feedbackCadastrado = feedbackRepository.save(feedback);
        return feedbackCadastrado;
    }

    public Feedback alterar(Feedback feedback) {
        Feedback feedbackBanco = buscarPorId(feedback.getId());
        feedbackBanco.setComentario(feedback.getComentario());
        feedbackBanco.setNota(feedback.getNota());
        feedbackBanco.setDataHora(feedback.getDataHora());
        return feedbackRepository.save(feedbackBanco);
    }

    public void excluir(Long id) {
        Feedback feedbackBanco = buscarPorId(id);
        feedbackRepository.delete(feedbackBanco);
    }

    public Page<Feedback> buscarTodos(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    public Feedback buscarPorId(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao(messageSource
                .getMessage("feedback.notfound",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

}
