package org.lessons.booleaners.ticketplatform.repo;

import org.lessons.booleaners.ticketplatform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
    Optional<Ticket> findByStatus(Ticket.Status status);
    Ticket findById(int id);
    List<Ticket> findByUserId(int id);
}
