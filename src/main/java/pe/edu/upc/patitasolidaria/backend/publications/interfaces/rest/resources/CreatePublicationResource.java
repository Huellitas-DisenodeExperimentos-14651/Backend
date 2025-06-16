package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources;

public record CreatePublicationResource(
        String title,
        String description,
        String contactInfo,
        String location,
        Long petId
) {}
