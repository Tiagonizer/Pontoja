package com.pontoja.demo.repository;

import com.pontoja.demo.model.RegistrarPonto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RegistrarPontoRepository extends MongoRepository<RegistrarPonto, String> {
    List<RegistrarPonto> findByEmpregadoId(String empregadoId);
}