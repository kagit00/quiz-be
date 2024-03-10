package com.quiz.quizapp.service;

import com.quiz.quizapp.model.DonationAmount;
import com.quiz.quizapp.model.DonationOrderDetails;

public interface DonationService {
    DonationOrderDetails createOrder(DonationAmount amount);
}
