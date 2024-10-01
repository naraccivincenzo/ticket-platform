package org.lessons.booleaners.ticketplatform.service;

import org.lessons.booleaners.ticketplatform.model.Note;
import org.lessons.booleaners.ticketplatform.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repository;

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Optional<Note> getNoteById(int id) {
        return repository.findById(id);
    }

    public Note create(Note note) {
        return repository.save(note);
    }

    public Note update(Note note) {
        return repository.save(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }
}
