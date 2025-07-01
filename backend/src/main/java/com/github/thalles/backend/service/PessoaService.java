package com.github.thalles.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.github.thalles.backend.exception.NaoEncontradoExcecao;
import com.github.thalles.backend.model.Pessoa;
import com.github.thalles.backend.repository.PessoasRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoasRepository pessoaRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmailServices emailService;

    public Pessoa inserir(Pessoa pessoa) {
        Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
        emailService.enviarEmailSimples(pessoaCadastrada.getEmail(), "Cadastrado com sucesso!", "Cadastrado no Sistema de Leilão foi feito com sucesso!");
        //enviarEmailSucesso(pessoaCadastrada);
        return pessoaCadastrada;
    }

    private void enviarEmailSucesso(Pessoa pessoa) {
        Context context = new Context();
        context.setVariable("nome", pessoa.getNome());
        emailService.emailTemplate(pessoa.getEmail(), "Cadastro feito com Sucesso", context, "cadastroSucesso");
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

    public Page<Pessoa> buscarTodos(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
        .orElseThrow(() -> new NaoEncontradoExcecao(messageSource
        .getMessage("pessoa.notfound",
        new Object[] {id},
        LocaleContextHolder.getLocale() )));
    }
}
