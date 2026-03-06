package com.example.core_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity avisa ao Spring (JPA/Hibernate) para transformar essa classe em uma tabela no banco de dados H2 automaticamente
@Entity
public class Feedback {

    @Id // define o campo id como chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incremento (1, 2, 3, ...)
    private Long id;

    private String text;
    private String sentiment;
    private Double score;

    // construtor vazio necessário para o JPA/Hibernate
    public Feedback() {}

    // getters e setters para os acessar e modificar os campos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
}

