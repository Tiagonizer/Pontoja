package com.pontoja.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "registrarponto")
public class RegistrarPonto {
    @Id
    private String id;

    @DBRef
    private Empregado empregado; // ManyToOne: vários pontos para um empregado

    private LocalDateTime dataHora;

    private String justificativa;

    private String tipo; // "ENTRADA" ou "SAIDA"

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Empregado getEmpregado() { return empregado; }
    public void setEmpregado(Empregado empregado) { this.empregado = empregado; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getJustificativa() { return justificativa; }
    public void setJustificativa(String justificativa) { this.justificativa = justificativa; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}