package com.splitwise.clone.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Repositories.UserDao;

@Service
public class Userservice {
    @Autowired
    UserDao userdao;

    public User addUser(User user) {
        return userdao.save(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userdao.findAll());
    }

    public Optional<User> getUser(int id) {
        return userdao.findById(id);
    }

    public User updateUser(int id, User userNew) {
        Optional<User> optionalUser = userdao.findById(id);

        return optionalUser.map(userOld -> {
            userOld.setUserName(userNew.getUserName());
            userOld.setEmail(userNew.getEmail());
            userOld.setImageUrl(userNew.getImageUrl());
            userOld.setPhoneNo(userNew.getPhoneNo());
            return userdao.save(userOld);
        }).orElse(null);
    }

    public String deleteUser(int id) {
        userdao.deleteById(id);
        return "Deleted user";
    }
}
