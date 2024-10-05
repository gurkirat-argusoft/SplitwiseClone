package com.splitwise.clone.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Repositories.UserDao;
import com.splitwise.clone.Services.FriendService;

@RestController
@RequestMapping("/friend")
@CrossOrigin(origins = "http://localhost:4200")
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    UserDao userDao;

    @PostMapping("/addfriend/{userId}/{friendId}")
    public ResponseEntity<Optional<User>> addFriend(@PathVariable("userId") int userId,
            @PathVariable("friendId") int friendId) {
        try {
            friendService.addFriend(userId, friendId);
            return new ResponseEntity<>(userDao.findById(friendId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Server error occured", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallfriends/{userId}")
    public ResponseEntity<List<User>> getAllFriends(@PathVariable("userId") int userId) {

        try {
            return new ResponseEntity<>(friendService.getAllFriends(userId), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity("no friends found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{userId}/{friendId}")
    public ResponseEntity<String> deleteFriend(@PathVariable("userId") int userId , @PathVariable("friendId") int friendId){
        try{
        friendService.deleteFriend(userId, friendId);
        return new ResponseEntity<>("friend deleted",HttpStatus.GONE);
           }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }   }
}
