package com.dev.moviebookingsystem.bmt.model;

import com.dev.moviebookingsystem.bmt.model.constant.AuditoriumFeature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Movie extends BaseEntity{
    private String name;
    private String description;
    private String language;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeature> movieFeatures;
}
