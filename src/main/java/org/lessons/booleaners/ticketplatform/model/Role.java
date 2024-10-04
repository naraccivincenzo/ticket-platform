package org.lessons.booleaners.ticketplatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String role;

    public int getId() {
        return id;
    }

    //Getter and Setter

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank String getRole() {
        return role;
    }

    public void setRole(@NotBlank String role) {
        this.role = role;
    }
}
