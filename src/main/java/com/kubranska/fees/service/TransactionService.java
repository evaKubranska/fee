package com.kubranska.fees.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final FeeCalculation feeCalculation;

    public Double getFee(String transactionType) {
        return feeCalculation.calculateFee(transactionType);
    }

    public Double getFees(Map<String, Integer> transactionTypes) {
        return feeCalculation.calculateFees(transactionTypes);
    }
}
