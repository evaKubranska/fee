package com.kubranska.fees.repository;

import com.kubranska.fees.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {

    Optional<Fee> findByTransactionType(String transactionType);

    List<Fee> findByTransactionTypeIn(Set<String> transactionType);
}
