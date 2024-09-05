package com.splitwise.clone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.splitwise.clone.Entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction,Integer>{

    @Query(value = "select * from transaction",nativeQuery = true)
    public Transaction addTransactionByReceiver(int giverid,int receiverId);

    @Query(value = "select * from transaction",nativeQuery = true)
    public Transaction addTransactionByEvent(int userId,int eventId);

    @Query(value = "select * from transaction",nativeQuery = true)
    public Transaction getAllTransactionByUser(int userId);

    @Query(value = "select * from transaction",nativeQuery = true)
    public Transaction getTransactionByEvent(int userId,int eventId);

    @Query(value = "select * from transaction",nativeQuery = true)
    public Transaction getAllTransactionByEvent(int eventId);

   
    @Query(value = "select * from transaction",nativeQuery = true)
    public Transaction getAllTransactionByReceiver(int receiverId);


}
