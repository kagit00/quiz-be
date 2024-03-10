package com.quiz.quizapp.dao;

import com.quiz.quizapp.model.DonationOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationOrderDao extends JpaRepository<DonationOrderDetails, Long> {
}
