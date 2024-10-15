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

    public Set<Group> getUserGroups(int userId) {
        return groupDao.userGroups(userId);
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
            return groupDao.save(oldGroup);
        }).orElse(null);
    }

    public String deleteGroup(int id) {
        groupDao.deleteById(id);
        groupDao.deleteGroupData(id);
        return "Deleted Group";
    }

    public List<User> getUsersInGroup(int groupId) {
        List<Object[]> results = groupDao.usersInGroupRaw(groupId);
        List<User> users = new ArrayList<>();

        for (Object[] row : results) {
            User user = new User();
            user.setUserId((int) row[0]);
            user.setEmail((String) row[1]);
            user.setImageUrl((String) row[2]);
            user.setPhoneNo((String) row[3]);
            user.setUserName((String) row[4]);
            users.add(user);
        }

        return users;
    }

    public Optional<Group> addMember(String userName, int groupId) {
        User user=userDao.findByUserName(userName);
        groupDao.addMember(groupId, user.getUserId());
       return groupDao.findById(groupId);
    }

    public void deleteMember(int userId , int groupId){
        groupDao.deleteMember(groupId, userId);

    }
}
