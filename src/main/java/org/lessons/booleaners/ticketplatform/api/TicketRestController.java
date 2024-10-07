package org.lessons.booleaners.ticketplatform.api;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/title")
    public List<Ticket> title(@RequestParam(name ="query", required = false) String query) {
        List<Ticket> result;
        if (query != null && !query.isEmpty()) {
            result = ticketService.findByTitle(query);
        }else {
            result = ticketService.getAllTickets();
        }
        return result;
    }

    @GetMapping("/status")
    public ResponseEntity<List<Ticket>> status(@RequestParam(name = "query", required = false) Ticket.Status query) {
        if (query != null) {
            List<Ticket> tickets = ticketService.findByStatus(query);
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<List<Ticket>> category(@RequestParam(name = "query", required = false) String query) {
        if (query != null && !query.isEmpty()) {
            List<Ticket> tickets = ticketService.findByCategory(query);
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
