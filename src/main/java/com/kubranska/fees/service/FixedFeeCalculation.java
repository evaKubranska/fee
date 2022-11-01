package com.kubranska.fees.service;

import com.kubranska.fees.model.Fee;
import com.kubranska.fees.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class FixedFeeCalculation implements FeeCalculation {

    private final FeeRepository feeRepository;

    @Override
    public Double calculateFee(String transactionType) {
        Fee fee = feeRepository.findByTransactionType(transactionType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fee not found"));
        return fee.getFee();
    }

    @Override
    public Double calculateFees(Map<String, Integer> transactionTypes) {
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        List<Fee> feeList = feeRepository.findByTransactionTypeIn(transactionTypes.keySet());
        feeList.forEach(fee -> {
            sum.updateAndGet(v -> v + (transactionTypes.get(fee.getTransactionType()) * fee.getFee()));
            transactionTypes.remove(fee.getTransactionType());
        });

        if (!transactionTypes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fee with transaction types : " + transactionTypes.keySet() + " not found");
        }
        return sum.get();
    }

}
