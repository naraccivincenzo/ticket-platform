package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TicketService tktservice;

    @GetMapping
    public String adminIndex(Model model,
                             Authentication authentication,
                             @RequestParam(name = "title", required = false)
                             String title) {
        List<Ticket> tickets;
        if (title != null && !title.isEmpty()) {
            tickets = tktservice.findByTitle(title);
        }else {
            tickets = tktservice.getAllTickets();
        }

        model.addAttribute("username", authentication.getName());
        model.addAttribute("tickets", tickets);
        return "/admin/ticketIndex";
    }

}
