package com.pontoja.demo.controller;

import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.model.Empregado.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String redirecionarPorRole(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof Empregado)) {
            return "redirect:/login";
        }
        Empregado empregado = (Empregado) authentication.getPrincipal();
        if (empregado.getRole() == UserRole.EMPREGADO) {
            return "redirect:/ponto/listar";
        } else if (empregado.getRole() == UserRole.EMPREGADOR) {
            return "redirect:/empregados/listar";
        }
        return "redirect:/login";
    }
}