package com.splitwise.clone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splitwise.clone.Entities.User;

public interface Userdao extends JpaRepository<User,Integer> {
    
}
