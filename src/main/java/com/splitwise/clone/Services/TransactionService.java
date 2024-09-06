package com.splitwise.clone.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.splitwise.clone.Entities.Transaction;
import com.splitwise.clone.Repositories.TransactionDao;
import com.splitwise.clone.Repositories.UserDao;

@Service
public class TransactionService {
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    UserDao userDao;

    public Transaction addTransaction(Transaction transaction){
        return transactionDao.save(transaction);     
    }

    public List<Transaction> allTransactionsByUser(int userId){
       return new ArrayList<>(transactionDao.getAllTransactionByUser(userId));
    }

    public List<Transaction> allTransactionsByReceiver(int receiverId){
        return new ArrayList<>(transactionDao.getAllTransactionByReceiver(receiverId));
    } 

    public List<Transaction> allUserTransactionsByEvent(int userId , int eventId){
        return new ArrayList<>(transactionDao.getUserTransactionByEvent(userId, eventId));
    }  

    public List<Transaction> getAllTransactionByEvent(int eventId){
        return new ArrayList<>(transactionDao.getAllTransactionByEvent(eventId));
    }  


}
