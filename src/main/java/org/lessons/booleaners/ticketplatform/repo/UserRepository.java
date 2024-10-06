package org.lessons.booleaners.ticketplatform.repo;

import org.lessons.booleaners.ticketplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByAvailability(boolean availability);
}
