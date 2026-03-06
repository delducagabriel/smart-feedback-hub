package com.example.core_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core_backend.model.Feedback;

// extende JpaRepository para herdar métodos de CRUD para a entidade Feedback
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

