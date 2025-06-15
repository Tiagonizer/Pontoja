package com.pontoja.demo.controller;

import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.model.RegistrarPonto;
import com.pontoja.demo.repository.EmpregadoRepository;
import com.pontoja.demo.repository.RegistrarPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Empregado empregado = empregadoRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado: " + username));
        List<RegistrarPonto> pontos = registrarPontoRepository.findByEmpregado_Id(empregado.getId());
        model.addAttribute("empregado", empregado);
        model.addAttribute("pontos", pontos);
        return "registrarponto";
    }

    @PostMapping("/registrar")
    public String registrarPonto(@RequestParam(value = "justificativa", required = false) String justificativa) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Empregado empregado = empregadoRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado: " + username));

        // Descobre quantos pontos já foram registrados hoje
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicio = hoje.atStartOfDay();
        LocalDateTime fim = hoje.plusDays(1).atStartOfDay();
        List<RegistrarPonto> pontosHoje = registrarPontoRepository.findByEmpregado_IdAndDataHoraBetween(
            empregado.getId(), inicio, fim
        );

        String tipo = pontosHoje.size() % 2 == 0 ? "ENTRADA" : "SAIDA";

        RegistrarPonto ponto = new RegistrarPonto();
        ponto.setEmpregado(empregado);
        ponto.setDataHora(LocalDateTime.now());
        ponto.setJustificativa(justificativa);
        ponto.setTipo(tipo);
        registrarPontoRepository.save(ponto);
        return "redirect:/ponto/listar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute Empregado empregado) {
        empregadoRepository.save(empregado);
        return "redirect:/ponto/listar";
    }
}