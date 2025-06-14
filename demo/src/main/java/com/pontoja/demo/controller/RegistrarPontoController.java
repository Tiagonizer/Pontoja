package com.pontoja.demo.controller;

import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.model.RegistrarPonto;
import com.pontoja.demo.repository.EmpregadoRepository;
import com.pontoja.demo.repository.RegistrarPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/ponto")
public class RegistrarPontoController {

    @Autowired
    private RegistrarPontoRepository registrarPontoRepository;
    
    @Autowired
    private EmpregadoRepository empregadoRepository;

    @GetMapping("/listar")
    public String listarPontos(Model model) {
        List<RegistrarPonto> pontos = registrarPontoRepository.findAll();
        model.addAttribute("pontos", pontos);
        return "registrarponto";
    }

    @PostMapping("/registrar")
    public String registrarPonto(@RequestParam String empregadoId) {
        RegistrarPonto ponto = new RegistrarPonto();
        Empregado empregado = empregadoRepository.findById(empregadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado: " + empregadoId));
        ponto.setEmpregado(empregado);
        ponto.setDataHora(LocalDateTime.now());
        registrarPontoRepository.save(ponto);
        return "redirect:/ponto/listar";
    }
}