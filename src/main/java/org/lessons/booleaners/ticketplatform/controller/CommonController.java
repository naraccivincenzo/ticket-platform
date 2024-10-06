package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.model.Note;
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
    private UserService userService;

    @GetMapping
    public String home() {
        return "/common/homepage";
    }
    @GetMapping("/common/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("note", noteService.findNoteByTicketId(id));
        model.addAttribute("ticket", ticketService.findById(id));
        return "/common/ticketShow";
    }

    @GetMapping("/common/{id}/note")
    public String createNote(@PathVariable("id") Integer id, Model model) {
        Note note = new Note();
        note.setTicket(ticketService.findById(id));
        model.addAttribute("note", note);
        return "/common/noteCreate";
    }

    @PostMapping("/common/{id}/note")
    public String storeNote(@PathVariable Integer id, @ModelAttribute("note") Note formNote,
                        BindingResult bindingResult,
                        RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/common/noteCreate";
        }

        formNote.setTicket(ticketService.findById(id));
        formNote.setAuthor(userService.getCurrentUsername());
        formNote.setId(null);
        noteService.create(formNote);
        attributes.addFlashAttribute("createMessage", "New note " + formNote.getNote() + " was successfully inserted");

        return "redirect:/common/" + id;
    }

}
