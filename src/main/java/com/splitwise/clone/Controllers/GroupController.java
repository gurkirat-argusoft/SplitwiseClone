package com.splitwise.clone.Controllers;

import java.util.ArrayList;
import java.util.List;

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
import com.splitwise.clone.Entities.User;
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

    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<Group>> getAllGroups(@PathVariable("id")int id){
        try {
            return new ResponseEntity<>(groupService.getAllGroups(id),HttpStatus.FOUND);
           } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           }
    }

    @GetMapping("/get/{id}")
    public Group getGroupById(@PathVariable("id") int id){
        return new Group();
    }

    @PutMapping("/update/{id}")
    public Group updateGroup(@PathVariable("id") int id ){
        return new Group();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") int id ){
        return "Deleted Group";
    }

    @PutMapping("/addMember/{groupId}/{userId}")
    public User addMember(@PathVariable("groupId") int groupId,@PathVariable("userId") int userId){
        return new User();
    }
}
