package com.github.thalles.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thalles.backend.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
