package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.CreatePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources.CreatePublicationResource;

public class CreatePublicationCommandFromResourceAssembler {
    public static CreatePublicationCommand toCommandFromResource(CreatePublicationResource resource) {
        return new CreatePublicationCommand(
                resource.title(),
                resource.description(),
                resource.contactInfo(),
                resource.location(),
                resource.petId()
        );
    }
}
