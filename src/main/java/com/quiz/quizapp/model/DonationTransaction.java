package com.quiz.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DonationTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String timestamp;
    private String status;
    @Column(nullable = true)
    private int userId;
    private String paymentId;
    private String signature;
    private String orderId;
}
