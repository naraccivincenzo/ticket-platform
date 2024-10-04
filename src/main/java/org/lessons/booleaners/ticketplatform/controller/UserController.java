package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.lessons.booleaners.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TicketService tktservice;

    @Autowired
    private UserService userservice;

    @GetMapping
    public String userIndex(Model model) {

        Integer userId = userservice.getCurrentUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("tickets", tktservice.findByUser(userId));
        return "/user/operator";
    }

}
