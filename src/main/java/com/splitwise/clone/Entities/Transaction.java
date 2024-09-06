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

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int transactionId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giverId", nullable = false)
    private User giverId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiverId")
    private User receiverId;

    @Column( nullable = false)
    private int amount;

    @Column(name = "doneAt", nullable = false)
    private LocalDateTime doneAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId")
    private Event event;
}
