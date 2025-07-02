package pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.aggregates.AdoptionRequest;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.interfaces.rest.resources.AdoptionRequestResource;

@Component
public class AdoptionRequestResourceFromEntityAssembler {
    public static AdoptionRequestResource toResourceFromEntity(AdoptionRequest entity) {
        return new AdoptionRequestResource(
                entity.getId(),
                entity.getPublication().getId(),
                entity.getApplicant().getId(),
                entity.getApplicant().getName(), // ✅ aquí usas el nombre del perfil
                entity.getReasonMessage().getValue(),
                entity.getStatus(),
                entity.getRequestDate()
        );
    }
}
