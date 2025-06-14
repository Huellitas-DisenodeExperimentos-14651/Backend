package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.UpdatePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources.PetResource;

public record UpdatePetCommandFromResourceAssembler() {
    public static UpdatePetCommand toCommandFromResource(Long petId, PetResource resource) {
        return new UpdatePetCommand(
                petId,
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
