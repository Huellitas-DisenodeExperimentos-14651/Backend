package pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Email(String value) {

    public Email() {
        this(null);
    }

    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }

        // Validación básica de formato de correo electrónico
        if (!value.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}$")) {
            throw new IllegalArgumentException("Email format is invalid");
        }
    }
}
