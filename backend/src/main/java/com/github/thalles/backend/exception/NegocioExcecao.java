package com.github.thalles.backend.exception;

public class NegocioExcecao extends RuntimeException {
    
    public NegocioExcecao(String mensagem) {
        super(mensagem);
    }
}
