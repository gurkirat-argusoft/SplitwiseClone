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

import com.splitwise.clone.Entities.Group;
import com.splitwise.clone.Entities.User;

@RestController
@RequestMapping("/group")
public class GroupController {
    @PostMapping("/create")
    public Group createGroup(@RequestBody Group group){
        return new Group();
    }

    @GetMapping("/getAll")
    public List<Group> getAllGroups(){
        return new ArrayList<>();
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

    @PostMapping("/addMember/{groupId}/{userId}")
    public User addMember(@PathVariable("groupId") int groupId,@PathVariable("userId") int userId){
        return new User();
    }
}
