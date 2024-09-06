package com.splitwise.clone.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.splitwise.clone.Entities.Group;
import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Repositories.GroupDao;
import com.splitwise.clone.Repositories.UserDao;

@Service
public class GroupService {
    @Autowired
    GroupDao groupDao;
    @Autowired
    UserDao userDao;

    public Group createGroup(Group group) {
        return groupDao.save(group);
    }

    public List<Group> getAllGroups(int userId) {
        return new ArrayList<>(groupDao.findAll());
    }

    public Optional<Group> getGroupById(int id) {
        return groupDao.findById(id);
    }

    public List<Group> getGroupByName(String Name) {
        return new ArrayList<>(groupDao.findByGroupName(Name));
    }

    public Group updateGroup(int groupId, Group newGroup) {
        Optional<Group> optionalGroup = groupDao.findById(groupId);
        return optionalGroup.map(oldGroup -> {
            oldGroup.setGroupName(newGroup.getGroupName());
            oldGroup.setImageUrl(newGroup.getImageUrl());
            oldGroup.setEvents(newGroup.getEvents());
            oldGroup.setUsers(newGroup.getUsers());
            return groupDao.save(oldGroup);
        }).orElse(null);
    }

    public String deleteGroup(int id) {
        groupDao.deleteById(id);
        return "Deleted Group";
    }

    public List<User> getUserInGroup(int groupId) {
        return groupDao.usersInGroup(groupId);

    }

    public Group addMember(int userId, String userName, int groupId) {
        Optional<Group> Optionalgroup = groupDao.findById(groupId);
        List<User> users = getUserInGroup(groupId);
        users.add(userDao.findByUserName(userName));
        return Optionalgroup.map(group -> {
            group.setUsers(users);
            return groupDao.save(group);
        }).orElse(null);
    }
}
