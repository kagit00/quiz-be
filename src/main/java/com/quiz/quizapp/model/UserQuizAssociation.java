package com.quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuizAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    private int score;
    private String timeTaken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date quizDate;
}
