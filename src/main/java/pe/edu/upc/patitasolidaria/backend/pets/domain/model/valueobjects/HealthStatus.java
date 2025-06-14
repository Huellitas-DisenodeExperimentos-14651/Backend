package pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record HealthStatus(String value) {
    public HealthStatus() {
        this(null);
    }

    public HealthStatus {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Breed cannot be null or blank");
        }
    }
}