package com.quiz.quizapp.model;

import com.quiz.quizapp.validation.ValidOptions;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "question_id", length = 36)
    private UUID questionId;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    @NotNull(message = "correct answer can't be null")
    @NotEmpty(message = "correct answer can't be empty")
    private String correctAnswer;
    @NotNull(message = "content can't be null")
    @NotEmpty(message = "content can't be empty")
    @Column(columnDefinition = "TEXT")
    private String content;
    @ValidOptions
    private List<String> options = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;
}
