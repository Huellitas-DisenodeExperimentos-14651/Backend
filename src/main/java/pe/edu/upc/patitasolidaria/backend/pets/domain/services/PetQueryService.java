package pe.edu.upc.patitasolidaria.backend.pets.domain.services;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates.Pet;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PetQueryService {
    List<Pet> handle(GetAllPetsQuery query);
    Optional<Pet> handle(GetPetByIdQuery query);
    List<Pet> handle(GetPetsByStatusQuery query);
}
