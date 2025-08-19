package com.github.thalles.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.github.thalles.backend.dto.PessoaAutenticacaoDTO;
import com.github.thalles.backend.dto.PessoaRequisicaoDTO;
import com.github.thalles.backend.model.Pessoa;
import com.github.thalles.backend.repository.PessoaRepository;
import com.github.thalles.backend.security.JwtService;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtservice;

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaAutenticacaoDTO autenticar(PessoaRequisicaoDTO pessoa) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha()));
        Pessoa pessoaBanco = pessoaRepository.findByEmail(pessoa.getEmail()).get();

        PessoaAutenticacaoDTO autenticacaoDTO = new PessoaAutenticacaoDTO();
        autenticacaoDTO.setEmail(pessoaBanco.getEmail());
        autenticacaoDTO.setNome(pessoaBanco.getNome());
        autenticacaoDTO.setToken(jwtservice.generateToken(authentication.getName()));

        return autenticacaoDTO;
    }
}
