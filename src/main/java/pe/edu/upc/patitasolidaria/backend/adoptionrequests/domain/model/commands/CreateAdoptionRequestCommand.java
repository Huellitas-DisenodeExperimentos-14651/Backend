package pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands;

public record CreateAdoptionRequestCommand(
        Long publicationId,        // ID de la publicación que está solicitando adoptar
        Long applicantProfileId,   // ID del perfil del usuario que quiere adoptar
        String reasonMessage       // Mensaje del solicitante explicando por qué quiere adoptar
) {}
