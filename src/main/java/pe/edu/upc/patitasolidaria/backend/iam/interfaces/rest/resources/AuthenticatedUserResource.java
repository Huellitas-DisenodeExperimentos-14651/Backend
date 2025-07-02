package pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(
        Long id,
        String username,
        Long profileId,
        String token,
        String role
) {}