package org.lessons.booleaners.ticketplatform.service;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.lessons.booleaners.ticketplatform.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Ticket> findByStatus(Ticket.Status status) {
        return repository.findByStatus(status);
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

    public void delete(Ticket ticket) {
        repository.delete(ticket);
    }
}
