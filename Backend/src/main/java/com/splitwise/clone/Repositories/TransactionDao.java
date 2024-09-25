package com.splitwise.clone.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.clone.Entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findAllByGiverId(int giverId);

    @Query(value = "select transaction.transaction_id, transaction.amount, transaction.done_at , transaction.giver_id , transaction.receiver_id , transaction.event_id from transaction where event_id = :eventId and giver_id= :giverId", nativeQuery = true)
    public List<Transaction> getUserTransactionByEvent(@Param("giverId") int giverId, @Param("eventId") int eventId);

    public List<Transaction> findAllByEventId(int eventId);

    @Query(value = "select transaction.transaction_id, transaction.amount, transaction.done_at , transaction.giver_id , transaction.receiver_id , transaction.event_id from transaction where receiver_id = :receiverId and giver_id= :giverId", nativeQuery = true)
    public List<Transaction> getAllTransactionByReceiver(@Param("receiverId") int receiverId,
            @Param("giverId") int giverId);

}
