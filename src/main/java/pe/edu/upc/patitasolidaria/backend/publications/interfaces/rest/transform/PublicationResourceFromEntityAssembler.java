package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources.PublicationResource;

@Component
public class PublicationResourceFromEntityAssembler {
    public static PublicationResource toResourceFromEntity(Publication entity) {
        return new PublicationResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getContactInfo().value(),
                entity.getLocation().value(),
                entity.getPublishedAt(),
                entity.getPetId(),
                entity.isActive()
        );
    }
}
