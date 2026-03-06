package com.example.core_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.core_backend.dto.NlpDTO;
import com.example.core_backend.model.Feedback;
import com.example.core_backend.repository.FeedbackRepository;

@Service // indica que a classe contém a lógica de negócio do sistema
public class FeedbackService {

    private final FeedbackRepository repository;
    private final RestTemplate restTemplate;

    // injeção de dependências > o spring entrega o repository pronto pra uso
    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate(); // cliente HTTP do java para fazer requisições a outros serviços
    }

    public Feedback processAndSave(String text) {
        // prepara o envio para o NLP service 
        String nlpUrl = "http://localhost:5000/analyze"; 
        
        NlpDTO.Request requestPayload = new NlpDTO.Request(text);
        NlpDTO.Response nlpResponse = restTemplate.postForObject(
            nlpUrl, 
            requestPayload, 
            NlpDTO.Response.class
        );

        Feedback feedback = new Feedback();
        feedback.setText(text);
        
        if (nlpResponse != null) {
            feedback.setSentiment(nlpResponse.sentiment());
            feedback.setScore(nlpResponse.score());
        } else {
            feedback.setSentiment("DESCONHECIDO");
            feedback.setScore(0.0);
        }

        return repository.save(feedback);
    }
    
    public java.util.List<Feedback> getAllFeedbacks() {
        return repository.findAll();
    }
}

