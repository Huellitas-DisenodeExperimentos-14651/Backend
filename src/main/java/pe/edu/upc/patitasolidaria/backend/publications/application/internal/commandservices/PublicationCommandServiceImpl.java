package pe.edu.upc.patitasolidaria.backend.publications.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.CreatePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.UpdatePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands.DeletePublicationCommand;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.valueobjects.ContactInfo;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.valueobjects.Location;
import pe.edu.upc.patitasolidaria.backend.publications.domain.services.PublicationCommandService;
import pe.edu.upc.patitasolidaria.backend.publications.infrastructure.persistence.jpa.repositories.PublicationRepository;

import java.util.Optional;

@Service
public class PublicationCommandServiceImpl implements PublicationCommandService {

    private final PublicationRepository publicationRepository;

    public PublicationCommandServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Long handle(CreatePublicationCommand command) {
        var contactInfo = new ContactInfo(command.contactInfo());
        var location = new Location(command.location());

        var publication = new Publication(
                command.title(),
                command.description(),
                contactInfo,
                location,
                command.petId()
        );

        try {
            this.publicationRepository.save(publication);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving publication: " + e.getMessage());
        }

        return publication.getId();
    }

    @Override
    public Optional<Publication> handle(UpdatePublicationCommand command) {
        var publicationId = command.id();

        var optionalPublication = this.publicationRepository.findById(publicationId);
        if (optionalPublication.isEmpty()) {
            throw new IllegalArgumentException("Publication with id " + publicationId + " does not exist");
        }

        var publicationToUpdate = optionalPublication.get();
        var contactInfo = new ContactInfo(command.contactInfo());
        var location = new Location(command.location());

        publicationToUpdate.update(
                command.title(),
                command.description(),
                contactInfo,
                location
        );

        try {
            var updated = this.publicationRepository.save(publicationToUpdate);
            return Optional.of(updated);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating publication: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePublicationCommand command) {
        Long id = command.id();

        if (!this.publicationRepository.existsById(id)) {
            throw new IllegalArgumentException("Publication with id " + id + " does not exist");
        }

        try {
            this.publicationRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting publication: " + e.getMessage());
        }
    }
}
