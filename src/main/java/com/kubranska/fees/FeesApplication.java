package com.kubranska.fees;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubranska.fees.constant.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class FeesApplication {


    public static void main(String[] args) {
        SpringApplication.run(FeesApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(RabbitTemplate template) {
        return args -> {
            template.convertAndSend(Constant.QUEUE_TRANSACTION, "A");
            template.convertAndSend(Constant.QUEUE_TRANSACTION_MAP, createMessage());
        };
    }

    private Map<String, Integer> getMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 2);
        map.put("B", 1);
        return map;
    }

    private String createMessage() {
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(getMap());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Bean
    public Queue transactionQueue() {
        return new Queue(Constant.QUEUE_TRANSACTION, false);
    }

    @Bean
    public Queue transactionMapQueue() {
        return new Queue(Constant.QUEUE_TRANSACTION_MAP, false);
    }
}
