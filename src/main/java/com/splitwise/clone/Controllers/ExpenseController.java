package com.splitwise.clone.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @GetMapping("/all/{userId}")
    public void allExpenses(@PathVariable("userId") int userId) {

    }

    @GetMapping("/expensesByGroup/{userId}/{groupId}")
    public void expensesByGroup(@PathVariable("userId") int userId, @PathVariable("groupId") int groupId) {

    }

    @GetMapping("/expensesByEvent/{userId}/{eventId}")
    public void expensesByEvent(@PathVariable("userId") int userId, @PathVariable("eventId") int eventId) {

    }

    @GetMapping("/expensesByFriend/{userId}/{friendId}")
    public void expensesByFriend(@PathVariable("userId") int userId, @PathVariable("friendId") int friendId) {

    }

}
