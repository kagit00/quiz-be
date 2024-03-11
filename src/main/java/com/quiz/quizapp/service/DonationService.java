package com.quiz.quizapp.service;

import com.quiz.quizapp.model.DonationAmount;
import com.quiz.quizapp.model.DonationOrderDetails;
import com.quiz.quizapp.model.DonationTransaction;

public interface DonationService {
    DonationOrderDetails createOrder(DonationAmount amount);
    void saveTransaction(DonationTransaction transaction);
}
