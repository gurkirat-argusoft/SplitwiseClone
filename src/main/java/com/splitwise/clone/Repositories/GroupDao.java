package com.splitwise.clone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.clone.Entities.Group;
import com.splitwise.clone.Entities.User;
import java.util.List;


public interface GroupDao extends JpaRepository<Group, Integer> {
    @Query(value = "Select * from group ",nativeQuery = true)
    public User addMember(User user);
    
    public List<Group> findByGroupName(String groupName);

    @Query(value = "select user.user_id,user.email, user.image_url,user.phone_no,user.user_name from user join user_groups on user.user_id=user_groups.user_id where user_groups.group_id= :groupId",nativeQuery = true)
    public List<User> usersInGroup(@Param("groupId")int groupId);
}
