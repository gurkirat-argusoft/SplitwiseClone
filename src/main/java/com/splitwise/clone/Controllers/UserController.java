package com.splitwise.clone.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.clone.Entities.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("add")
    public User addUser(){
        return new User();
    }    

    @GetMapping("getAll")
    public List<User> getAllUsers(){
        return new ArrayList<>();
    }

    
}
