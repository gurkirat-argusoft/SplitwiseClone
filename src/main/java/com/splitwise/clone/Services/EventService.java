package com.splitwise.clone.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splitwise.clone.Entities.Event;
import com.splitwise.clone.Entities.User;
import com.splitwise.clone.Repositories.EventDao;
import com.splitwise.clone.Repositories.UserDao;

@Service
public class EventService {
    @Autowired
    EventDao eventDao;

    @Autowired
    UserDao userDao;
    public Event addEvent(Event event){
        return eventDao.save(event);
    }

    public String deleteEvent(int id){
        eventDao.deleteById(id);
        return "Event deleted";
    }

    public List<User> getEventMembers(int eventId){
        return eventDao.usersInEvent(eventId);
    }
}
