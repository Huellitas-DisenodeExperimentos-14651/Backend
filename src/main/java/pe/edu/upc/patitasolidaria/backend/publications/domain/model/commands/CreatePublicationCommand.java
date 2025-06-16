package pe.edu.upc.patitasolidaria.backend.publications.domain.model.commands;

public record CreatePublicationCommand(
        String title,
        String description,
        String contactInfo,
        String location,
        Long petId // se asume que la publicación está ligada a una mascota existente
) {}
