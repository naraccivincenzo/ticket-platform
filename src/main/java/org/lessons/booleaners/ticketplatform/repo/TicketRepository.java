package org.lessons.booleaners.ticketplatform.repo;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
    List<Ticket> findByStatus(Ticket.Status status);
    Ticket findById(int id);
    List<Ticket> findByUserId(int id);
    List<Ticket> findByCategoryContainingIgnoreCase(String title);
}
