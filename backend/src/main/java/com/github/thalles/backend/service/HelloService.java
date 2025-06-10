package com.github.thalles.backend.service;

import org.springframework.stereotype.Service;

import com.github.thalles.backend.model.Calculadora;

@Service
public class HelloService {
    

    public Calculadora somar(Calculadora calculadora) {
        calculadora.setResultado(calculadora.getValor1() + calculadora.getValor2());
        return calculadora;
    }
}
