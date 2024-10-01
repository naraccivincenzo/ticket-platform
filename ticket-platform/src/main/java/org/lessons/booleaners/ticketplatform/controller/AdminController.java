package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String adminIndex(Model model,
                             Authentication authentication,
                             @RequestParam(name = "title", required = false)
                             String title) {
        List<Ticket> tickets;
        if (title != null && !title.isEmpty()) {
            tickets = ticketService.findByTitle(title);
        }else {
            tickets = ticketService.getAllTickets();
        }

        model.addAttribute("username", authentication.getName());
        model.addAttribute("tickets", tickets);
        return "/admin/ticketIndex";
    }

}
