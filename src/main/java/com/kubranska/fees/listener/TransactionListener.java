package com.kubranska.fees.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubranska.fees.constant.Constant;
import com.kubranska.fees.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class TransactionListener {

    private final TransactionService transactionService;

    @RabbitListener(queues = Constant.QUEUE_TRANSACTION)
    public void receive(String message) {
        Double fee = transactionService.getFee(message);
        log.info("Received transaction type {} , calculated fee is {}", message, fee);
    }

    @RabbitListener(queues = Constant.QUEUE_TRANSACTION_MAP)
    public void receiveTransactionMap(String message) {
        Double fee = transactionService.getFees(convertToMap(message));
        log.info("Received transaction type {} , calculated fee is {}", message, fee);
    }

    private Map<String, Integer> convertToMap (String message) {
        Map<String, Integer> transactionTypes = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            transactionTypes = mapper.readValue(message, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return transactionTypes;
    }

}
