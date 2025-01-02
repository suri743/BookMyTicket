package com.dev.moviebookingsystem.bmt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Theater extends BaseEntity{
    private String name;
    private String address;

    @OneToMany
    @JoinColumn(name = "theatre_id")
    private List<Auditorium> auditoriums;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @JsonBackReference
    private City city;
}
