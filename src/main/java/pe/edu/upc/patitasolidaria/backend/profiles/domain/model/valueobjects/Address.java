package pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String value) {

    public Address() {
        this(null);
    }

    public Address {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or blank");
        }
    }
}
