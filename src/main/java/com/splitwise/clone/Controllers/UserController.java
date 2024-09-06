package com.splitwise.clone.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") int id){
        return new User();
    }

    @PutMapping("/update/{id}")
    public User updatUser(@PathVariable("id") int id,@RequestBody User user){
        return new User();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        return "User deleted";
    }

}
