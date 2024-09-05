package com.splitwise.clone.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.clone.Entities.Transaction;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @PostMapping("/addbyreceiver/{giverId}/{receiverId}")
    public Transaction addTransactionByReceiver(@PathVariable("giverId") int giverId,@PathVariable("receiverId") int receiverId){
        return new Transaction();
    }
    @PostMapping("/addbyevent/{giverId}/{eventId}")
    public Transaction addTransactionByEvent(@PathVariable("giverId") int giverId,@PathVariable("eventId") int eventId){
        return new Transaction();
    }


    @GetMapping("/getAll/{userId}")
    public List<Transaction> allTransactionsByUser(@PathVariable("userId") int userId){
        return new ArrayList<>();
    }

    @GetMapping("/getbyreceiver/{giverId}/{receiverId}")
    public List<Transaction> getTransactionsByReceiver(@PathVariable("giverId") int giverId,@PathVariable("receiverId") int receiverId){
        return new ArrayList<>();
    }

    @GetMapping("/getbyevent/{giverId}/{eventId}")
    public List<Transaction> getTransactionsByEvent(@PathVariable("giverId") int giverId,@PathVariable("eventId") int eventId){
        return new ArrayList<>();
    }
}
