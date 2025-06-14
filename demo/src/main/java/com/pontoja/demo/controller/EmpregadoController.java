package com.pontoja.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.repository.EmpregadoRepository;

import java.util.List;

@Controller
//@RequestMapping("/api/empregados")
@CrossOrigin(origins = "*")
public class EmpregadoController {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @PostMapping("/cadastrar")
    public Empregado cadastrar(@RequestBody Empregado empregado) {
        return empregadoRepository.save(empregado);
    }

    @GetMapping
    public String listarTodos(Model model) {
        List<Empregado> empregados = empregadoRepository.findAll();
        model.addAttribute("empregados", empregados);
        return "listafuncionarios";
    }

    @PostMapping("/login")
    public Empregado login(@RequestBody Empregado credenciais) {
        return empregadoRepository.findByEmail(credenciais.getEmail())
                .filter(e -> e.getSenha().equals(credenciais.getSenha()))
                .orElse(null);
    }
}
