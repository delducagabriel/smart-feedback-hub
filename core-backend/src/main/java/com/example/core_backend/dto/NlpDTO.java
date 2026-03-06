package com.example.core_backend.dto;

// classe para organizar os dados de NLP que serão enviados ou recebidos pela API
public class NlpDTO {
    public record Request(String text) {}
    public record Response(String text, String sentiment, Double score) {}
}

/*
Não podemos expor diretamente a classe já que ela pode conter informações sensíveis, então usamos DTOs pra formatar a entrada e saída
Record é uma classe imutável que é ideal para representar dados simples, como esses, ela tem constantes e métodos de acesso automáticos, o que facilita a criação de objetos de forma concisa e segura
 */