package com.quiz.quizapp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Success {
    private String uid;
    private String timestamp;
    private Object body;
}
