package pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PetSize {
    SMALL,
    MEDIUM,
    LARGE;

    @JsonCreator
    public static PetSize from(String value) {
        return PetSize.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase(); // opcional: para que se serialice en minúsculas también
    }
}