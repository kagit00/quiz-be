package com.quiz.quizapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "donation_order_details")
public class DonationOrderDetails {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amountPaid;
    private int amount;
    private int amountDue;
    private String createdAt;
    private String entity;
    private String status;
    private int attempts;
    private String currency;
    private String receipt;
    @JsonIgnore
    private String offerId;
    private String orderId;
    private String key;
    @Column(nullable = true)
    private int userId;
}
