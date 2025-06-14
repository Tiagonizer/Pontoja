package com.pontoja.demo.controller;

import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.model.Empregado.UserRole;
import com.pontoja.demo.repository.EmpregadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empregados")
@CrossOrigin(origins = "*")
public class EmpregadoController {

    @Autowired
    private EmpregadoRepository empregadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute Empregado empregado) {
        empregado.setSenha(passwordEncoder.encode(empregado.getSenha())); // Codifica a senha antes de salvar)
        empregadoRepository.save(empregado);
        return "redirect:/empregados/listar";
    }

    @GetMapping("/listar")
    public String listarTodos(Model model) {
        List<Empregado> empregados = empregadoRepository.findAll();
        model.addAttribute("empregados", empregados);
        return "listafuncionarios";
    }

    @GetMapping("/cadastrar")
    public String login() {
        return "cadastro";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable String id, Model model) {
        Empregado empregado = empregadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado: " + id));
        model.addAttribute("empregado", empregado);
        model.addAttribute("roles", UserRole.values());
        return "editar-empregado";
    }

    @PostMapping("/editar/{id}")
    public String atualizarEmpregado(@PathVariable String id, @ModelAttribute("empregado") Empregado empregadoAtualizado) {
        Empregado empregado = empregadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empregado não encontrado: " + id));

        empregado.setNome(empregadoAtualizado.getNome());
        empregado.setEmail(empregadoAtualizado.getEmail());
        empregado.setRole(empregadoAtualizado.getRole());

        // Atualiza a senha apenas se foi preenchida
        if (empregadoAtualizado.getSenha() != null && !empregadoAtualizado.getSenha().isEmpty()) {
            empregado.setSenha(passwordEncoder.encode(empregadoAtualizado.getSenha()));
        }

        empregadoRepository.save(empregado);
        return "redirect:/empregados/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirEmpregado(@PathVariable String id) {
        empregadoRepository.deleteById(id);
        return "redirect:/empregados/listar";
    }
}
