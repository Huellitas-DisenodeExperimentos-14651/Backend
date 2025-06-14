package pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Breed(String value) {
    public Breed() {
        this(null);
    }

    public Breed {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Breed cannot be null or blank");
        }
    }
}