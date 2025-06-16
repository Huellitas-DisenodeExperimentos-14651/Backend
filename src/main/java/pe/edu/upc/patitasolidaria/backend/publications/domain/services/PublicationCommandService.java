package pe.edu.upc.patitasolidaria.backend.publications.domain.services;

import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.CreatePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.UpdatePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.DeletePublicationCommand;

import java.util.Optional;

public interface PublicationCommandService {
    Long handle(CreatePublicationCommand command);
    Optional<Publication> handle(UpdatePublicationCommand command);
    void handle(DeletePublicationCommand command);
}
