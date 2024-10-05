package com.splitwise.clone.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Repositories.UserDao;

@Service
public class FriendService {
    @Autowired
    UserDao userDao;

    public Optional<User> addFriend(int userId , int friendId){
        userDao.addFriend(userId, friendId);
        return userDao.findById(friendId);
    }

    public User getFriendFromUsername(String friendName){
        return userDao.findByUserName(friendName);
    }

    public List<User> getAllFriends(int userId){
        return userDao.getAllFriends(userId);
    }

    public void deleteFriend(int userId , int friendId){
         userDao.deleteFriend(userId, friendId);
    }
}
