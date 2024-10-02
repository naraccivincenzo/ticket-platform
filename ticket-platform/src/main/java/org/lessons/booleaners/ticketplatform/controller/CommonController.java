package org.lessons.booleaners.ticketplatform.controller;

import org.lessons.booleaners.ticketplatform.model.Note;
import org.lessons.booleaners.ticketplatform.service.NoteService;
import org.lessons.booleaners.ticketplatform.service.TicketService;
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

    @GetMapping
    public String homepage() {
        return "/common/homepage";
    }
    @GetMapping("/common/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("note", noteService.findNoteByTicketId(id));
        model.addAttribute("ticket", ticketService.findById(id));
        return "/common/ticketShow";
    }

    @GetMapping("/{id}/note")
    public String create(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketService.findById(id));
        model.addAttribute("note", new Note());
        return "/common/noteCreate";
    }

    @PostMapping("/{id}/note")
    public String store(@PathVariable Integer id, @ModelAttribute("note") Note formNote,
                        BindingResult bindingResult,
                        RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/common/noteCreate";
        }
        formNote.setId(id);
        noteService.create(formNote);
        attributes.addFlashAttribute("createMessage", "Special Offer " + formNote.getNote() + " was successfully inserted");

        return "redirect:/common/" + formNote.getTicket().getId();
    }


}
