package pe.edu.upc.patitasolidaria.backend.adoptionrequests.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.aggregates.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.*;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects.ReasonMessage;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects.RequestStatus;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.services.AdoptionRequestCommandService;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.infrastructure.persistence.jpa.repositories.AdoptionRequestRepository;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates.Pet;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.PetStatus;
import pe.edu.upc.patitasolidaria.backend.pets.infrastructure.persistence.jpa.repositories.PetRepository;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.patitasolidaria.backend.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.publications.infrastructure.persistence.jpa.repositories.PublicationRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AdoptionRequestCommandServiceImpl implements AdoptionRequestCommandService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final ProfileRepository profileRepository;
    private final PublicationRepository publicationRepository;
    private final PetRepository petRepository;

    public AdoptionRequestCommandServiceImpl(
            AdoptionRequestRepository adoptionRequestRepository,
            ProfileRepository profileRepository,
            PublicationRepository publicationRepository,
            PetRepository petRepository
    ) {
        this.adoptionRequestRepository = adoptionRequestRepository;
        this.profileRepository = profileRepository;
        this.publicationRepository = publicationRepository;
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateAdoptionRequestCommand command) {
        // Verificar si la publicación existe
        Publication publication = publicationRepository.findById(command.publicationId())
                .orElseThrow(() -> new IllegalArgumentException("Publication not found with ID: " + command.publicationId()));

        // Verificar si el perfil del solicitante existe
        Profile applicantProfile = profileRepository.findById(command.applicantProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Applicant profile not found with ID: " + command.applicantProfileId()));

        // Verificar que no exista una solicitud duplicada
        if (adoptionRequestRepository.existsByPublicationIdAndApplicant_Id(
                command.publicationId(), applicantProfile.getId())) {
            throw new IllegalArgumentException("Duplicate request: This applicant has already sent a request for this publication");
        }

        // Crear la solicitud
        var request = new AdoptionRequest(publication, applicantProfile, new ReasonMessage(command.reasonMessage()));
        adoptionRequestRepository.save(request);

        return request.getId();
    }

    @Override
    @Transactional
    public void handle(ApproveAdoptionRequestCommand command) {
        AdoptionRequest request = adoptionRequestRepository.findById(command.adoptionRequestId())
                .orElseThrow(() -> new IllegalArgumentException("AdoptionRequest not found with ID: " + command.adoptionRequestId()));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be approved.");
        }

        request.approve();
        adoptionRequestRepository.save(request);

        // 🟢 Desactivar publicación
        Publication publication = request.getPublication();
        publication.deactivate(); // Esto ya cambia isActive a false
        publicationRepository.save(publication);

        // 🟢 Cambiar estado de la mascota a ADOPTED
        Pet pet = petRepository.findById(publication.getPetId())
                .orElseThrow(() -> new IllegalArgumentException("Pet not found with ID: " + publication.getPetId()));
        pet.updateInformation(
                pet.getName(),
                pet.getAge(),
                pet.getPhoto(),
                pet.getBreed(),
                pet.getSize(),
                PetStatus.ADOPTED,
                pet.getDescription(),
                pet.getHealthStatus(),
                pet.getVaccinationStatus(),
                pet.getSpecialNeeds()
        );
        petRepository.save(pet);
    }


    @Override
    @Transactional
    public void handle(RejectAdoptionRequestCommand command) {
        AdoptionRequest request = adoptionRequestRepository.findById(command.adoptionRequestId())
                .orElseThrow(() -> new IllegalArgumentException("AdoptionRequest not found with ID: " + command.adoptionRequestId()));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be rejected.");
        }

        request.reject();
        adoptionRequestRepository.save(request);
    }
}
