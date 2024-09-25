package com.splitwise.clone.Controllers;

import java.util.*;

import org.apache.el.stream.Optional;
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

import com.splitwise.clone.Entities.Group;
import com.splitwise.clone.Repositories.UserDao;
import com.splitwise.clone.Services.GroupService;
import com.splitwise.clone.Services.Userservice;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    UserDao userDao;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            return new ResponseEntity<>(groupService.createGroup(group), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getusergroups/{id}")
    public ResponseEntity<Set<Group>> getUserGroups(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(groupService.getUserGroups(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getuseringroup/{id}")
    public ResponseEntity<Object> getUserInGroup(@PathVariable("id") int groupId) {
        try {
            return new ResponseEntity<>(groupService.getUsersInGroup(groupId), HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable("id") int id, @RequestBody Group group) {
        if (groupService.getGroupById(id) != null) {
            return new ResponseEntity<>(groupService.updateGroup(id, group), HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(groupService.deleteGroup(id), HttpStatus.GONE);
        } catch (Exception e) {
            return new ResponseEntity("Server error occured.", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/addmember/{groupId}/{userName}")
    public ResponseEntity<Group> addMember(@PathVariable("groupId") int groupId,
            @PathVariable("userName") String userName) {
        if (groupService.getGroupById(groupId) != null && userDao.findByUserName(userName) != null) {
            return new ResponseEntity<>(groupService.addMember(userName, groupId), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Server error occured.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
