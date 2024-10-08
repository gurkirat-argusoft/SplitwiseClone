package com.splitwise.clone.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.*;

@Entity
@Data
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "eventId")
  private int eventId;
  @Column(nullable = false)
  private String description;
  @Column(name = "happenedAt", nullable = false)
  private LocalDateTime happenedAt;
  private int amount;
  @Column(name = "splitType", nullable = false)
  private String splitType;
  @Column(name="group_id",nullable = true)
  private String groupId;

}
