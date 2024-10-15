package com.splitwise.clone.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
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

    public Event addEvent(Event event) {
        return eventDao.save(event);
    }

    public String deleteEvent(int id) {
        eventDao.deleteById(id);
        return "Event deleted";
    }

    public Optional<Event> addMember(String userName, int eventId) {
        User user=userDao.findByUserName(userName);
        eventDao.addMember(user.getUserId(), eventId);
        return eventDao.findById(eventId);
    }

    public Set<User> getEventMembers(int eventId) {
        Set<Object[]> results = eventDao.usersInEvent(eventId);
        Set<User> users = new HashSet<>();

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
    public String deleteMember(int userId,int eventId){
        eventDao.deleteMember(eventId, userId);
        return "Member deleted";
    }
    public Set<Event> getAllEventsByUser(int userId) {
        return eventDao.eventByUser(userId);
    }
    public Set<Event> getEventByGroup(int groupId) {
        return eventDao.findAllByGroupId(groupId);
    }

    public Event updateEvent(int eventId, Event newEvent) {
        Optional<Event> optionalEvent = eventDao.findById(eventId);
        return optionalEvent.map(event -> {
            event.setAmount(newEvent.getAmount());
            event.setDescription(newEvent.getDescription());
            // event.setGroup(newEvent.getGroup());
            event.setHappenedAt(newEvent.getHappenedAt());
            event.setSplitType(newEvent.getSplitType());
            // event.setUsers(newEvent.getUsers());
            return eventDao.save(event);
        }).orElse(null);
    }

}
