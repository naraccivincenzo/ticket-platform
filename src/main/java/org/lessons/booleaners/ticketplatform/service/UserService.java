package org.lessons.booleaners.ticketplatform.service;

import org.lessons.booleaners.ticketplatform.model.User;
import org.lessons.booleaners.ticketplatform.repo.UserRepository;
import org.lessons.booleaners.ticketplatform.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<User> findByAvailability(boolean availability) {
        return repository.findByAvailability(availability);
    }


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            return authentication.getName();
        }
    }

    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof DatabaseUserDetails userDetails) {
            return userDetails.getId();
        } else {
            return null;
        }
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
