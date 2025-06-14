package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetSize;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;

public record CreatePetResource(
        String name,
        int age,
        String photo,
        String breed,
        PetSize size,
        PetStatus status,
        String description,
        String healthStatus,
        String vaccinationStatus,
        String specialNeeds
) {}
