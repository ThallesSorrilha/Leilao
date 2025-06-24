package com.github.thalles.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.github.thalles.backend.exception.NaoEncontradoExcecao;
import com.github.thalles.backend.model.Pessoa;
import com.github.thalles.backend.repository.PessoasRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoasRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    public Pessoa inserir(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa alterar(Pessoa pessoa) {
        Pessoa pessoaBanco = buscarPorId(pessoa.getId());
        pessoaBanco.setNome(pessoa.getNome());
        pessoaBanco.setEmail(pessoa.getEmail());
        return pessoaRepository.save(pessoaBanco);
    }

    public void excluir(Long id) {
        Pessoa pessoaBanco = buscarPorId(id);
        pessoaRepository.delete(pessoaBanco);
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
        .orElseThrow(() -> new NaoEncontradoExcecao(messageSource
        .getMessage("pessoa.notfound",
        new Object[] {id},
        LocaleContextHolder.getLocale() )));
    }
}
