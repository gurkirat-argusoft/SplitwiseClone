package com.splitwise.clone.Repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splitwise.clone.Entities.Event;

public interface EventDao extends JpaRepository<Event, Integer> {
    @Query(value = "select user.user_id,user.email, user.image_url,user.phone_no,user.user_name from user join user_events on user_events.user_id=user.user_id where user_events.event_id= :eventId", nativeQuery = true)
    public Set<Object[]> usersInEvent(@Param("eventId") int eventId);

    @Query(value = "select event.event_id , event.amount , event.description , event.happened_at , event.split_type , group_id from event join user_events on  user_events.event_id=event.event_id where user_events.user_id= :userId", nativeQuery = true)
    public Set<Event> eventByUser(@Param("userId") int userId);
}
