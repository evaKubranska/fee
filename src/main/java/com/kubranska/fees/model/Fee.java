package com.kubranska.fees.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fee {

    @Id
    @Column(unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "type", nullable = false, unique = true, length = 2)
    private String transactionType;

    @Column(nullable = false, scale = 20, precision = 2)
    private Double fee;
}