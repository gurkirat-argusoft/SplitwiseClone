package com.splitwise.clone.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_groups")
@Data
public class UserGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "group_id")
    private int groupId;

}
