package pe.edu.upc.patitasolidaria.backend.publications.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ContactInfo(String value) {
    public ContactInfo() {
        this(null);
    }

    public ContactInfo {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ContactInfo cannot be null or blank");
        }
        if (!value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$") &&
                !value.matches("^[0-9+\\-\\s]{7,15}$")) {
            throw new IllegalArgumentException("ContactInfo must be a valid email or phone number");
        }
    }
}
