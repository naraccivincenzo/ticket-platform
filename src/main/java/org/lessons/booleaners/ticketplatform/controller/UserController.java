package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.model.User;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.lessons.booleaners.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userservice;
    @Autowired
    private UserService userService;


    @GetMapping
    public String userIndex(Model model) {

        Integer userId = userservice.getCurrentUserId();
        List<Ticket> userTickets = ticketService.findByUser(userId);
        model.addAttribute("userId", userId);

        boolean canUpdateStatus = userTickets.stream()
                .allMatch(ticket -> ticket.getStatus() == Ticket.Status.completed);
        model.addAttribute("canUpdateStatus", canUpdateStatus);
        model.addAttribute("tickets", ticketService.findByUser(userId));
        return "/user/operator";
    }

    @PostMapping
    public String updateUserStatus(@RequestParam("availability") boolean availability) {

        Integer userId = userservice.getCurrentUserId();
        List<Ticket> userTickets = ticketService.findByUser(userId);
        Optional<User> user = userservice.findById(userId);

        boolean allTicketsCompleted = userTickets.stream()
                .allMatch(ticket -> ticket.getStatus() == Ticket.Status.completed);

        if (allTicketsCompleted) {
            user.get().setAvailability(availability);
            userService.update(user.get());
        }

        return "redirect:/user";
    }

}
