package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.model.User;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.lessons.booleaners.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TicketService tktservice;

    @Autowired
    private UserService userservice;
    @Autowired
    private UserService userService;

    @GetMapping
    public String userIndex(Model model) {

        Integer userId = userservice.getCurrentUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("tickets", tktservice.findByUser(userId));
        return "/user/operator";
    }

    @PostMapping
    public String updateAvailability(Model model, User formUser) {
        Integer userId = userservice.getCurrentUserId();
        Optional<User> user = userservice.findById(userId);
        user.get().setAvailability(formUser.getAvailability());
        userService.update(user.get());
        model.addAttribute("tickets", tktservice.findByUser(userId));

        return "/user/operator";
    }

}
