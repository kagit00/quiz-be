package com.quiz.quizapp.service;

import com.quiz.quizapp.dao.DonationOrderDao;
import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.model.DonationAmount;
import com.quiz.quizapp.model.DonationOrderDetails;
import com.quiz.quizapp.util.DefaultValuesPopulator;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DonationServiceImpl implements DonationService {
    @Value("${razorpay.key.id}")
    private String keyId;
    @Value("${razorpay.key.secret}")
    private String keySecret;

    private final DonationOrderDao donationOrderDao;

    public DonationServiceImpl(DonationOrderDao donationOrderDao) {
        this.donationOrderDao = donationOrderDao;
    }

    @Override
    public DonationOrderDetails createOrder(DonationAmount amount) {
        try {
            long amt = Long.parseLong(amount.getAmount());
            var client = new RazorpayClient(keyId, keySecret);

            JSONObject obj = new JSONObject();
            obj.put("amount", amt*100);
            obj.put("currency", "INR");
            obj.put("receipt", "txn_" + DefaultValuesPopulator.getUid());
            Order order = client.orders.create(obj);
            return saveOrder(order, keyId);
        } catch (RazorpayException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    private DonationOrderDetails saveOrder(Order order, String key) {
        DonationOrderDetails donationOrderDetails = new DonationOrderDetails();

        donationOrderDetails.setAttempts(order.get("attempts"));
        donationOrderDetails.setCurrency(order.get("currency"));
        donationOrderDetails.setAmount(order.get("amount"));
        donationOrderDetails.setEntity(order.get("entity"));
        donationOrderDetails.setOrderId(order.get("id"));
        donationOrderDetails.setStatus(order.get("status"));
        donationOrderDetails.setAmountDue(order.get("amount_due"));
        donationOrderDetails.setAmountPaid(order.get("amount_paid"));
        donationOrderDetails.setReceipt(order.get("receipt"));
        donationOrderDetails.setCreatedAt(order.get("created_at").toString());
        donationOrderDetails.setKey(key);

        return donationOrderDao.save(donationOrderDetails);
    }
}
