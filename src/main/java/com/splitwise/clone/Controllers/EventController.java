package com.splitwise.clone.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.clone.Entities.Event;
import com.splitwise.clone.Entities.User;

import lombok.Data;
import java.util.*;

@RestController
@Data
@RequestMapping("/event")
public class EventController {
    @GetMapping("eventsbyuser/{userId}")
    public List<Event> eventsByUser(@PathVariable("userId") int userId){
        return new ArrayList<>();
    }

    @PostMapping("/addevent")
    public Event addEvent(@RequestBody Event event){
        return new Event();
    }

    @DeleteMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") int eventId){
        return "Event Deleted";
    }

    @PutMapping("/update/{eventId}")
    public Event updatEvent(@PathVariable("eventId") int eventId,@RequestBody Event event){
        return new Event();
    }

    @GetMapping("/eventmembers/{eventId}")
    public List<User> eventMembers(@PathVariable("eventId") int eventId){
        return new ArrayList<>();
    }

    @PutMapping("/addMember/{eventId}/{userId}")
    public User addMember(@PathVariable("eventId") int eventId,@PathVariable("userId") int userId){
        return new User();
    }

}
