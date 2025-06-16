package pe.edu.upc.patitasolidaria.backend.publications.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(String value) {
    public Location() {
        this(null);
    }

    public Location {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or blank");
        }
    }
}
