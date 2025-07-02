package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetSize;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePetResource(
        @NotBlank
        @Size(max = 50)
        String name,

        @NotNull
        @Min(0)
        Integer age,

        @Size(max = 300)
        String photo,

        @NotBlank
        @Size(max = 50)
        String breed,

        @NotNull
        PetSize size,

        @Size(max = 500)
        String description,

        @NotBlank
        @Size(max = 100)
        String healthStatus,

        @NotBlank
        @Size(max = 100)
        String vaccinationStatus,

        @Size(max = 300)
        String specialNeeds
) {}
