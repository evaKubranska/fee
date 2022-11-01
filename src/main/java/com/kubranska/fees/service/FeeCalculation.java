package com.kubranska.fees.service;

import java.util.List;
import java.util.Map;

public interface FeeCalculation {

    Double calculateFee(String transactionType);

    Double calculateFees(Map<String, Integer> transactionTypes);
}
