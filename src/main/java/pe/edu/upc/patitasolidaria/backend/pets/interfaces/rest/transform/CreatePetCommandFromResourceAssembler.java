package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.CreatePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources.CreatePetResource;

public class CreatePetCommandFromResourceAssembler {
    public static CreatePetCommand toCommandFromResource(CreatePetResource resource, Long profileId) {
        return new CreatePetCommand(
                resource.name(),
                resource.age(),
                resource.photo(),
                resource.breed(),
                resource.size(),
                PetStatus.AVAILABLE, // fijo aquí porque es creación
                resource.description(),
                resource.healthStatus(),
                resource.vaccinationStatus(),
                resource.specialNeeds(),
                profileId
        );
    }
}
