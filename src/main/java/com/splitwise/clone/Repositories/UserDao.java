package com.splitwise.clone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splitwise.clone.Entities.User;
import java.util.List;


public interface UserDao extends JpaRepository<User,Integer> {
    
    public User findByUserName(String userName);
}
