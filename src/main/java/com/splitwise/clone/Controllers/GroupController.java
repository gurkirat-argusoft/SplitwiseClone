package com.splitwise.clone.Controllers;

import java.util.ArrayList;
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
import com.splitwise.clone.Services.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
@Autowired
GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody Group group){
       try {
        return new ResponseEntity<>(groupService.createGroup(group),HttpStatus.CREATED);
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    @GetMapping("/getusergroups/{id}")
    public ResponseEntity<Set<Group>> getUserGroups(@PathVariable("id")int id){
        try {
            return new ResponseEntity<>(groupService.getUserGroups(id),HttpStatus.FOUND);
           } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
    }

    @GetMapping("/getuseringroup/{id}")
    public ResponseEntity<Object> getUserInGroup(@PathVariable("id") int groupId){
        try {
            return new ResponseEntity<>(groupService.getUsersInGroup(groupId),HttpStatus.FOUND);
           } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
      
    }

    @PutMapping("/update/{id}")
    public Group updateGroup(@PathVariable("id") int id ){
        return new Group();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") int id ){
        return "Deleted Group";
    }

    @PutMapping("/addmember/{groupId}/{userName}")
    public Group addMember(@PathVariable("groupId") int groupId,@PathVariable("userName") String userName){
        return groupService.addMember(userName, groupId);
    }
}
