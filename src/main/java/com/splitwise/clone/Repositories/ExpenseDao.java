package com.splitwise.clone.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.splitwise.clone.Entities.Transaction;

public interface ExpenseDao extends JpaRepository<Transaction, Integer>{

    @Query(value = "select * from transaction",nativeQuery = true)
    public List<Transaction> allExpensesByUser(int userId);
    
    @Query(value = "select * from transaction",nativeQuery = true)
    public List<Transaction> allExpensesByGroup(int userId, int groupId);

    @Query(value = "select * from transaction",nativeQuery = true)
    public List<Transaction> allExpensesByEvent(int userId, int eventId);

    @Query(value = "select * from transaction",nativeQuery = true)
    public List<Transaction> allExpensesByFriend(int userId , int friendId);
}
