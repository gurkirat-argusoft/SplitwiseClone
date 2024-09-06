package com.splitwise.clone.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.clone.Entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction,Integer>{

    @Query(value = "select * from transaction where giver_id = :giverId",nativeQuery = true)
    public List<Transaction> getAllTransactionByUser(@Param("giverId")int giverId);

    @Query(value = "select * from transaction where user_id = :userId and event_id = :eventId",nativeQuery = true)
    public List<Transaction> getUserTransactionByEvent(@Param("userId")int userId,@Param("eventId")int eventId);

    @Query(value = "select * from transaction where event_id = :eventId",nativeQuery = true)
    public List<Transaction> getAllTransactionByEvent(@Param("eventId")int eventId);
   
    @Query(value = "select * from transaction where receiver_id = :receiverId",nativeQuery = true)
    public List<Transaction> getAllTransactionByReceiver(@Param("receiverId")int receiverId);

}
