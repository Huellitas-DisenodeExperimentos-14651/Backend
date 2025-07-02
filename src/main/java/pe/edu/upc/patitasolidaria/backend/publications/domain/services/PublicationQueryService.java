package pe.edu.upc.patitasolidaria.backend.publications.domain.services;

import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PublicationQueryService {
    List<Publication> handle(GetAllPublicationsQuery query);
    Optional<Publication> handle(GetPublicationByIdQuery query);
    List<Publication> handle(GetPublicationsByPetIdQuery query);
    List<Publication> handle(GetActivePublicationsQuery query); // ✅ nuevo
}
