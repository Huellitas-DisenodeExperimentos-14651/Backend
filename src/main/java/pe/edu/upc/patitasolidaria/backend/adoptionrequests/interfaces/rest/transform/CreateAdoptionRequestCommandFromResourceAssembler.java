package pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.CreateAdoptionRequestCommand;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.resources.CreateAdoptionRequestResource;

public class CreateAdoptionRequestCommandFromResourceAssembler {

    public static CreateAdoptionRequestCommand toCommandFromResource(CreateAdoptionRequestResource resource, Long profileId) {
        return new CreateAdoptionRequestCommand(
                resource.publicationId(),
                profileId, // ✅ Ahora sí, viene como argumento
                resource.reasonMessage()
        );
    }
}
