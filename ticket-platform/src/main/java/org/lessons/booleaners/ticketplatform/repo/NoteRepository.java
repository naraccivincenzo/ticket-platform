package org.lessons.booleaners.ticketplatform.repo;

import org.lessons.booleaners.ticketplatform.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {
}
