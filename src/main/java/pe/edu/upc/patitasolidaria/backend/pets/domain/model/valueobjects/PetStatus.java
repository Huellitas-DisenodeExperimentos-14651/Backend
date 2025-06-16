package pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus {
    AVAILABLE,
    ADOPTED,
    FOSTERED;

    @JsonCreator
    public static PetStatus from(String value) {
        return PetStatus.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase(); // opcional
    }
}
