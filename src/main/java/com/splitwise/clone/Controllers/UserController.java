package com.splitwise.clone.Controllers;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Services.Userservice;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    Userservice userservice;

    @PostMapping("add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userservice.addUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("User not created.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getall")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userservice.getAllUsers(), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity("Server error occured.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> user = userservice.getUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatUser(@PathVariable("id") int id, @RequestBody User user) {
        if (userservice.getUser(id) != null) {
            return ResponseEntity.of(Optional.of(userservice.updateUser(id, user)));
        }
        return new ResponseEntity("Server error occured.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        if (userservice.getUser(id) != null) {
            return ResponseEntity.of(Optional.of(userservice.deleteUser(id)));
        }
        return new ResponseEntity("Server error occured.", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
