package com.splitwise.clone.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_events")
@Data
public class UserEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "event_id")
    private int eventId;
}
