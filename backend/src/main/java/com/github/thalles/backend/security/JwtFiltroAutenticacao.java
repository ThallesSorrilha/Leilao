package com.github.thalles.backend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.thalles.backend.service.PessoaService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFiltroAutenticacao extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PessoaService pessoaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = pessoaService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails.getUsername())) {
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Lista de caminhos que o filtro deve ignorar (os mesmos de permitAll)
        String path = request.getServletPath();
        return path.startsWith("/autenticacao") ||
               path.startsWith("/perfil") ||
               path.startsWith("/pessoa") ||
               path.startsWith("/categoria") ||
               path.startsWith("/feedback") ||
               path.startsWith("/imagem") ||
               path.startsWith("/lance") ||
               path.startsWith("/leilao") ||
               path.startsWith("/pagamento") ||
               path.startsWith("/login") || 
               path.startsWith("/cadastro") ||
               path.startsWith("/recuperar-senha") ||
               path.startsWith("/alterar-senha") ||
               path.startsWith("/inserir-codigo");
    }

}
