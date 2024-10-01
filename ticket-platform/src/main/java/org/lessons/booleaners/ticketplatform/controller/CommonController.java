package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String homepage() {
        return "/common/homepage";
    }
    @GetMapping("/common/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketService.findById(id));
        return "/common/ticketShow";
    }
}
