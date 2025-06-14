package pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates.Pet;
import pe.edu.upc.patitasolidaria.backend.pets.interfaces.rest.resources.PetResource;

@Component
public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet entity) {
        return new PetResource(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getPhoto(),
                entity.getBreed(),
                entity.getSize(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getHealthStatus(),
                entity.getVaccinationStatus(),
                entity.getSpecialNeeds()
        );
    }
}
