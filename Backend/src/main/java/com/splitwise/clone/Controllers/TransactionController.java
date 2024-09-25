package com.splitwise.clone.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.clone.Entities.Transaction;
import com.splitwise.clone.Repositories.EventDao;
import com.splitwise.clone.Repositories.UserDao;
import com.splitwise.clone.Services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserDao userDao;
    @Autowired
    EventDao eventDao;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(
                    transactionService.addTransaction(transaction.getGiverId(), transaction.getReceiverId(),
                            transaction.getAmount(), transaction.getDoneAt(), transaction.getEventId()),
                    HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<Transaction>> allTransactionsByUser(@PathVariable("userId") int userId) {
        if (userDao.findById(userId) != null) {
            return new ResponseEntity<>(transactionService.allTransactionsByUser(userId), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyreceiver/{giverId}/{receiverId}")
    public ResponseEntity<List<Transaction>> getTransactionsByReceiver(@PathVariable("giverId") int giverId,
            @PathVariable("receiverId") int receiverId) {
        if (userDao.findById(giverId) != null && userDao.findById(receiverId) != null) {
            return new ResponseEntity<>(transactionService.allTransactionsByReceiver(receiverId, giverId),
                    HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getusertransactionbyevent/{giverId}/{eventId}")
    public ResponseEntity<List<Transaction>> getUserTransactionsByEvent(@PathVariable("giverId") int giverId,
            @PathVariable("eventId") int eventId) {
        if (userDao.findById(giverId) != null && eventDao.findById(eventId) != null) {
            return new ResponseEntity<>(transactionService.allUserTransactionsByEvent(giverId, eventId),
                    HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getalltransactionbyevent/{eventId}")
    public ResponseEntity<List<Transaction>> getTransactionsByEvent(@PathVariable("eventId") int eventId) {
        if (eventDao.findById(eventId) != null) {
            return new ResponseEntity<>(transactionService.getAllTransactionByEvent(eventId), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
