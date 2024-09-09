package com.splitwise.clone.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.splitwise.clone.Entities.Event;
import com.splitwise.clone.Entities.Group;
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

    public Event addMember(String userName, int eventId) {
        Optional<Event> Optionalevent = eventDao.findById(eventId);
        User newUser = userDao.findByUserName(userName);
        return Optionalevent.map(event -> {
            Set<User> userList = event.getUsers();
            userList.add(newUser);
            event.setUsers(userList);
            return eventDao.save(event);
        }).orElse(null);
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

    public Set<Event> getAllEventsByUser(int userId) {
        return eventDao.eventByUser(userId);
    }

    public Event updateEvent(int eventId, Event newEvent) {
        Optional<Event> optionalEvent = eventDao.findById(eventId);
        return optionalEvent.map(event -> {
            event.setAmount(newEvent.getAmount());
            event.setDescription(newEvent.getDescription());
            event.setGroup(newEvent.getGroup());
            event.setHappenedAt(newEvent.getHappenedAt());
            event.setSplitType(newEvent.getSplitType());
            event.setUsers(newEvent.getUsers());
            event.setTransactions(newEvent.getTransactions());
            return eventDao.save(event);
        }).orElse(null);
    }
}
