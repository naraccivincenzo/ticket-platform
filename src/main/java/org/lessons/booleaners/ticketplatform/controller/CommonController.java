package org.lessons.booleaners.ticketplatform.controller;

import jakarta.validation.Valid;
import org.lessons.booleaners.ticketplatform.model.Note;
import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.service.NoteService;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.lessons.booleaners.ticketplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userservice;

    @GetMapping
    public String home() {
        return "/common/homepage";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("note", noteService.findNoteByTicketId(id));
        model.addAttribute("ticket", ticketService.findById(id));
        return "/common/ticketShow";
    }

    @GetMapping("/{id}/note")
    public String createNote(@PathVariable("id") Integer id, Model model) {
        Note note = new Note();
        note.setTicket(ticketService.findById(id));
        model.addAttribute("note", note);
        return "/common/noteCreate";
    }

    @PostMapping("/{id}/note")
    public String storeNote(@PathVariable Integer id,
                            @ModelAttribute("note") Note formNote,
                            BindingResult bindingResult,
                            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/common/noteCreate";
        }

        formNote.setTicket(ticketService.findById(id));
        formNote.setAuthor(userservice.getCurrentUsername());
        formNote.setId(null);
        noteService.create(formNote);
        attributes.addFlashAttribute("createMessage", "New note " + formNote.getNote() + " was successfully inserted");

        return "redirect:/common/" + id;
    }

    @GetMapping("/create")
    public String createTicket (Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("user", userservice.findByAvailability(true));
        return "/common/ticketCreate";
    }

    @PostMapping("/create")
    public String storeTicket(@Valid @ModelAttribute("ticket") Ticket formTicket,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userservice.findByAvailability(true));
            return "/common/ticketCreate";
        }
        ticketService.create(formTicket);
        attributes.addFlashAttribute("createMessage", "Ticket " + formTicket.getTitle() + " successfully inserted");
        if (userservice.getCurrentUserRole().equals("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editTicket (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketService.findById(id));
        model.addAttribute("user", userservice.findByAvailability(true));
        return "/common/ticketEdit";
    }

    @PostMapping("/edit/{id}")
    public String updateTicket(@PathVariable("id") Integer id,
                               @RequestParam("status") String status,
                               RedirectAttributes attributes) {
        Ticket ticket = ticketService.findById(id);

        ticket.setStatus(Ticket.Status.valueOf(status));
        ticketService.update(ticket);
        attributes.addFlashAttribute("createMessage", "Ticket " + ticket.getTitle() + " successfully updated");
        return "redirect:/user";
    }

}
