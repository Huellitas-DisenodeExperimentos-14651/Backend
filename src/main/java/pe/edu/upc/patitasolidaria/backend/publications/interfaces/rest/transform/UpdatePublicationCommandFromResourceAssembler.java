package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.UpdatePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources.PublicationResource;

public record UpdatePublicationCommandFromResourceAssembler() {
    public static UpdatePublicationCommand toCommandFromResource(Long publicationId, PublicationResource resource) {
        return new UpdatePublicationCommand(
                publicationId,
                resource.title(),
                resource.description(),
                resource.contactInfo(),
                resource.location()
        );
    }
}
