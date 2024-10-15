package com.splitwise.clone.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import com.splitwise.clone.Entities.Event;
import com.splitwise.clone.Entities.Transaction;
import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Repositories.TransactionDao;
import com.splitwise.clone.Repositories.UserDao;

@Service
public class TransactionService {
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    UserDao userDao;

    public Transaction addTransaction(int giverId, int receiverId, int amount, LocalDateTime doneAt, int eventId) {

        Transaction transaction = new Transaction();
        transaction.setGiverId(giverId);
        transaction.setReceiverId(receiverId);
        transaction.setAmount(amount);
        transaction.setDoneAt(doneAt);
        transaction.setEventId(eventId);
        return transactionDao.save(transaction);
    }

    public List<Transaction> allTransactionsByUser(int userId) {
        return new ArrayList<>(transactionDao.getAllTransactionsOfUser(userId));
    }

    public List<Transaction> allTransactionsByReceiver(int receiverId, int giverId) {
        return new ArrayList<>(transactionDao.getAllTransactionByReceiver(receiverId, giverId));
    }

    public List<Transaction> allUserTransactionsByEvent(int userId, int eventId) {
        return new ArrayList<>(transactionDao.getUserTransactionByEvent(userId, eventId));
    }

    public List<Transaction> getAllTransactionByEvent(int eventId) {
        return new ArrayList<>(transactionDao.findAllByEventId(eventId));
    }

}
