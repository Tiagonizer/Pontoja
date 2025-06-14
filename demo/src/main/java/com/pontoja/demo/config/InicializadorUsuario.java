package com.pontoja.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pontoja.demo.model.Empregado;
import com.pontoja.demo.repository.EmpregadoRepository;

@Component
public class InicializadorUsuario implements CommandLineRunner {

    private final EmpregadoRepository empregadoRepository;
    private final PasswordEncoder encoder;

    public InicializadorUsuario(EmpregadoRepository usuarioRepository, PasswordEncoder encoder) {
        this.empregadoRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (empregadoRepository.count() == 0) { // Verifica se não há usuários cadastrados
            Empregado admin = new Empregado();
            admin.setNome("admin");
            admin.setSenha(encoder.encode("senha123")); // Defina uma senha segura!
            admin.setEmail("admin@gmail.com");
            admin.setRole(Empregado.UserRole.EMPREGADOR); // Define o papel como EMPREGADOR");
            empregadoRepository.save(admin);
            System.out.println("Usuário administrador criado!");
        }
    }
}