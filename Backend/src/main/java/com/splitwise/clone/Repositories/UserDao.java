package com.splitwise.clone.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.clone.Entities.User;

import jakarta.transaction.Transactional;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    public User findByUserName(String userName);

    @Modifying
    @Transactional
    @Query(value = "insert into friends (user_id , friend_id) values(:userId,:friendId)",nativeQuery = true)
    public void addFriend(@Param("userId") int userId,@Param("friendId") int friendId);

    @Query(value = "select u.user_id , u.user_name,u.email , u.image_url,u.phone_no from  friends friend join user u on friend.friend_id=u.user_id where friend.user_id= :userId;",nativeQuery = true)
    public List<User> getAllFriends(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(value = "delete from friends where user_id = :userId and friend_id = :friendId",nativeQuery = true)
    public void deleteFriend(@Param("userId") int userId , @Param("friendId") int friendId);
}
