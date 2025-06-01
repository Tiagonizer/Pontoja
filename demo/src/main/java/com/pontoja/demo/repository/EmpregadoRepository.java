package com.pontoja.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.pontoja.demo.model.Empregado;

import java.util.Optional;

public interface EmpregadoRepository extends MongoRepository<Empregado, String> {
    Optional<Empregado> findByEmail(String email);
}
