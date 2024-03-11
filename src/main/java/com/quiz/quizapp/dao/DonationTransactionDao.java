package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.DonationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationTransactionDao extends JpaRepository<DonationTransaction, Integer> {
}
