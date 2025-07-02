package pe.edu.upc.patitasolidaria.backend.publications.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record UpdatePublicationResource(
        @NotBlank
        @Size(max = 100)
        String title,

        @NotBlank
        @Size(max = 1000)
        String description,

        @NotBlank
        @Size(max = 100)
        @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$|^[0-9+\\-\\s]{7,15}$")
        String contactInfo,

        @NotBlank
        @Size(max = 255)
        String location,

        boolean isActive
) {}
