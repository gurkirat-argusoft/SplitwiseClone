package com.splitwise.clone.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.clone.Entities.Event;
import com.splitwise.clone.Entities.User;

public interface EventDao extends JpaRepository<Event, Integer> {
     @Query(value = "select user.user_id,user.email, user.image_url,user.phone_no,user.user_name from user join user_events on user.user_id=user_events.user_id where user_events.event_id= :eventId",nativeQuery = true)
    public List<User> usersInEvent(@Param("eventId")int eventId);
    
   
}
