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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cid", length = 36)
    private UUID cid;
    @NotNull(message = "title can't be null")
    @NotEmpty(message = "title can't be empty")
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private Set<Quiz> quizzes = new LinkedHashSet<>();
}
