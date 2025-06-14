package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.CreatePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetSize;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources.CreatePetResource;

public class CreatePetCommandFromResourceAssembler {
    public static CreatePetCommand toCommandFromResource(CreatePetResource resource) {
        return new CreatePetCommand(
                resource.name(),
                resource.age(),
                resource.photo(),
                resource.breed(),
                resource.size(),
                resource.status(),
                resource.description(),
                resource.healthStatus(),
                resource.vaccinationStatus(),
                resource.specialNeeds()
        );
    }
}
