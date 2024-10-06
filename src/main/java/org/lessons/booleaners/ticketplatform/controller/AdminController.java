package org.lessons.booleaners.ticketplatform.controller;

import jakarta.validation.Valid;
import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.lessons.booleaners.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TicketService tktservice;

    @Autowired
    private UserService userservice;

    @GetMapping
    public String ticket(Model model,
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

    @GetMapping("/create")
    public String createTicket (Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("user", userservice.findByAvailability(true));
        return "/admin/ticketCreate";
    }

    @PostMapping("/create")
    public String storeTicket(@Valid @ModelAttribute("ticket") Ticket formTicket,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userservice.findByAvailability(true));
            return "/admin/ticketCreate";
        }
        tktservice.create(formTicket);
        attributes.addFlashAttribute("createMessage", "Ticket " + formTicket.getTitle() + " successfully inserted");
        return "redirect:/admin";
    }

}
