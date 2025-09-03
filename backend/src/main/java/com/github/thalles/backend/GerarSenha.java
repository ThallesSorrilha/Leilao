package com.github.thalles.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder enconde = new BCryptPasswordEncoder();
        System.out.println(enconde.encode("123"));
    }
}