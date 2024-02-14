package com.quiz.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "quiz_id", length = 36)
    private UUID quizId;
    private boolean isActive = false;
    @NotNull(message = "title can't be null")
    @NotEmpty(message = "title can't be empty")
    private String title;
    private String description;
    private int maxMarks;
    private int numberOfQuestions;
    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quiz", orphanRemoval = true)
    @JsonIgnore
    private Set<Question> questions = new LinkedHashSet<>();
}
