package com.quiz.quizapp.controllers;

import com.quiz.quizapp.model.DonationAmount;
import com.quiz.quizapp.model.Success;
import com.quiz.quizapp.service.DonationServiceImpl;
import com.quiz.quizapp.util.BasicUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/donation")
public class DonationController {

    private final DonationServiceImpl donationService;

    public DonationController(DonationServiceImpl donationService) {
        this.donationService = donationService;
    }
    /**
     * Create order response entity.
     *
     * @param amount the amount
     * @return the response entity
     */
    @PostMapping(value = "/create_order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Success> createOrder(@RequestBody DonationAmount amount) {
        return new ResponseEntity<>(BasicUtility.setSuccessBody(donationService.createOrder(amount)), HttpStatus.OK);
    }
}
