package com.splitwise.clone.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import java.time.*;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int transactionId;

    @Column(unique = false, nullable = false)
    private int giverId;

    @Column(unique = false, nullable = false)
    private int receiverId;

    @Column(nullable = false)
    private int amount;

    @Column(name = "doneAt", nullable = false)
    private LocalDateTime doneAt;

    @Column(nullable = true)
    private int eventId;
    private String description;
}
