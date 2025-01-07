package com.dev.moviebookingsystem.bmt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class User extends BaseEntity{
    private String name;
    private String email;
    private String mobileNumber;

    // One user can have multiple tickets
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Ticket> tickets;
}
