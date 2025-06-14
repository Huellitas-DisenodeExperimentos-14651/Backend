package pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VaccinationStatus(String value) {
    public VaccinationStatus() {
        this(null);
    }

    public VaccinationStatus {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Breed cannot be null or blank");
        }
    }
}