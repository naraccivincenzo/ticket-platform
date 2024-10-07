package org.lessons.booleaners.ticketplatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "note")
public class Note{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Lob
    private String note;

    @NotBlank
    private String author;

    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable=false)
    @JsonBackReference
    private Ticket ticket;

    //Getter and Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getNote() {
        return note;
    }

    public void setNote(@NotBlank String note) {
        this.note = note;
    }

    public @NotBlank String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank String author) {
        this.author = author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
