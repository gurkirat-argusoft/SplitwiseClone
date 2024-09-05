package com.splitwise.clone.Entities;

import java.util.*;
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
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private int userId;
    @Column(name = "userName",unique = true )
    private String userName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phoneNo")
    private String phoneNo;
    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Group> friendGroups = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "giverId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> givenTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiverId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "friends", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
            @JoinColumn(name = "friendId") })
    private Set<User> friends = new HashSet<>();

}
