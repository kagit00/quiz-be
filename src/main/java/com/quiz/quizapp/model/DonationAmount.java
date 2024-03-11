package com.quiz.quizapp.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DonationAmount {
    private String  amount;
    private int userId;
}
