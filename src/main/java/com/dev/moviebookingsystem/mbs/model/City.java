package com.dev.moviebookingsystem.mbs.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class City extends BaseEntity{
    private String name;
    private String state;

    @OneToMany
    @JoinColumn(name = "city_id")
    @JsonManagedReference
    private List<Theater> theaters;
}
