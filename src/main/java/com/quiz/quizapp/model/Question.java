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
    @NotNull(message = "content can't be null")
    @NotEmpty(message = "content can't be empty")
    private String content;
    private String img;
    @ValidOptions
    private List<String> options = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;
}
