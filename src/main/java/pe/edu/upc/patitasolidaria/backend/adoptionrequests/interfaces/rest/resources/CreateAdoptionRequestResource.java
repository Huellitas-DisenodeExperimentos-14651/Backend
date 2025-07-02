package pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAdoptionRequestResource(

        @NotNull
        Long publicationId,

        @NotBlank
        @Size(max = 500)
        String reasonMessage

) {}
