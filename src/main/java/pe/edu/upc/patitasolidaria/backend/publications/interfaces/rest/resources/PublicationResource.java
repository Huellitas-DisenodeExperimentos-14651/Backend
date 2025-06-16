package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources;

import java.time.LocalDateTime;

public record PublicationResource(
        Long id,
        String title,
        String description,
        String contactInfo,
        String location,
        LocalDateTime publishedAt,
        Long petId,
        boolean isActive
) {}
