package com.pontoja.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.repository.EmpregadoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/empregados")
@CrossOrigin(origins = "*")
public class EmpregadoController {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @PostMapping("/cadastrar")
    public Empregado cadastrar(@RequestBody Empregado empregado) {
        return empregadoRepository.save(empregado);
    }

    @GetMapping
    public List<Empregado> listarTodos() {
        return empregadoRepository.findAll();
    }

    @PostMapping("/login")
    public Empregado login(@RequestBody Empregado credenciais) {
        return empregadoRepository.findByEmail(credenciais.getEmail())
                .filter(e -> e.getSenha().equals(credenciais.getSenha()))
                .orElse(null);
    }
}
