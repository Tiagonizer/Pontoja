package com.pontoja.demo.repository;

import com.pontoja.demo.model.RegistrarPonto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface RegistrarPontoRepository extends MongoRepository<RegistrarPonto, String> {
    List<RegistrarPonto> findByEmpregado_Id(String empregadoId);
    List<RegistrarPonto> findByEmpregado_IdAndDataHoraBetween(String empregadoId, LocalDateTime inicio, LocalDateTime fim);
    List<RegistrarPonto> findByEmpregado_IdAndTipo(String empregadoId, String tipo);
}