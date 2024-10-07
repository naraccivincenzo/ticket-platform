package org.lessons.booleaners.ticketplatform.service;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }
    public Ticket findById(int id) {
        return repository.findById(id);
    }

    public List<Ticket> findByTitle(String title) {
        return repository.findByTitleContainingIgnoreCaseOrderByTitleAsc(title);
    }

    public List<Ticket> findByStatus(Ticket.Status status) {
        return repository.findByStatus(status);
    }

    public List<Ticket> findByCategory(String title) {
        return repository.findByCategoryContainingIgnoreCase(title);
    }

    public List<Ticket> findByUser(int userId) {
        return repository.findByUserId(userId);
    }

    public Ticket create(Ticket ticket) {
        return repository.save(ticket);
    }

    public Ticket update(Ticket ticket) {
        return repository.save(ticket);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
