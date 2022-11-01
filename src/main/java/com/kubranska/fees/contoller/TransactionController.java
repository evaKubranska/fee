package com.kubranska.fees.contoller;

import com.kubranska.fees.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "transaction", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{transactionType}")
    public ResponseEntity<Double> getFee(@PathVariable String transactionType) {
        return ResponseEntity.ok(transactionService.getFee(transactionType));
    }

    @PostMapping
    public ResponseEntity<Double> getFees(@RequestBody Map<String, Integer> transactionTypes) {
        return ResponseEntity.ok(transactionService.getFees(transactionTypes));
    }
}
