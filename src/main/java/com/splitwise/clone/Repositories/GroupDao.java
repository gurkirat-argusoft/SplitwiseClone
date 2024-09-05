package com.splitwise.clone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.splitwise.clone.Entities.Group;
import com.splitwise.clone.Entities.User;

public interface GroupDao extends JpaRepository<Group, Integer> {
    @Query(value = "Select * from group",nativeQuery = true)
    public User addMember(User user);
    
}
