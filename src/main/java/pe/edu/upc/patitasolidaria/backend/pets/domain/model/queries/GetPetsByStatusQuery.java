package pe.edu.upc.patitasolidaria.backend.pets.domain.model.queries;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;

public record GetPetsByStatusQuery(PetStatus status) {
}
