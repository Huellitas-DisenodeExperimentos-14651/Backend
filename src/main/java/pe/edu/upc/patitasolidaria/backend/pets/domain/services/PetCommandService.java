package pe.edu.upc.patitasolidaria.backend.pets.domain.services;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates.Pet;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.CreatePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.DeletePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.UpdatePetCommand;

import java.util.Optional;

public interface PetCommandService {
    Long handle(CreatePetCommand command);
    Optional<Pet> handle(UpdatePetCommand command);
    void handle(DeletePetCommand command);
}
