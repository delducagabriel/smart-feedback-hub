package com.example.core_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.core_backend.model.Feedback;
import com.example.core_backend.service.FeedbackService;

@RestController // indica que a classe é um controlador REST, ou seja, ela vai receber requisições HTTP e devolve JSON
@RequestMapping("/api/feedbacks") // caminho base para acessar os endpoints desse controlador
@CrossOrigin(origins = "*") // resolve o problema de CORS, mas em produção o * seria substituído pelo domínio do frontend
public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    // get retorna a lista de feedbacks salvos no banco de dados
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = service.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks); // 200 OK + lista de feedbacks no corpo da resposta
    }

    // record aninhado para mapear o JSON recebido do frontend {"text": "texto do feedback"}
    public record CreateFeedbackRequest(String text) {}

    // post recebe o feedback do frontend, processa e salva no banco de dados
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody CreateFeedbackRequest request) {
        Feedback savedFeedback = service.processAndSave(request.text());

        return ResponseEntity.ok(savedFeedback); // retorna objeto salvo com id e sentimento
    }
}

/* 
Não basta ter a regra de negócio e o banco de dados funcionando escondidos no servidor, precisamos expor para o frontend para que possa consumir. 
No padrão Spring Boot, a camada de controller é responsável por receber as requisições HTTP, chamar a camada de serviço para processar a lógica de negócio e devolver uma resposta HTTP.
CORS (Cross-Origin Resource Sharing) é um mecanismo de segurança implementado pelos navegadores para controlar quais domínios podem acessar recursos em um servidor.
Quando o frontend (que pode estar rodando em localhost:3000) tenta acessar a API do backend (que pode estar rodando em localhost:8080), o navegador bloqueia a requisição por questões de segurança, a menos que o backend permita explicitamente esse acesso. 
A anotação @CrossOrigin(origins = "*") é uma forma de dizer ao backend para permitir requisições de qualquer origem. 
*/