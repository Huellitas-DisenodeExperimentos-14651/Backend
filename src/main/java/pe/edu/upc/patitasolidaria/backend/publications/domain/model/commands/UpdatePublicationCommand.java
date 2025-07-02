package pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands;

public record UpdatePublicationCommand(
        Long id,
        String title,
        String description,
        String contactInfo,
        String location,
        boolean isActive
) {}
