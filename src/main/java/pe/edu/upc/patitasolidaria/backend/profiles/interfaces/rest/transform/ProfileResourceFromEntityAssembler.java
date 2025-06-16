package pe.edu.upc.patitasolidaria.backend.profiles.interfaces.rest.transform;

import org.springframework.stereotype.Component;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.patitasolidaria.backend.profiles.interfaces.rest.resources.ProfileResource;

@Component
public class ProfileResourceFromEntityAssembler {

    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getName(),
                entity.getEmail().value(),
                entity.getAddress().value(),
                entity.getRole().name(),
                entity.getPaymentMethods(),
                entity.getPreferences(),
                entity.getProfilePic(),
                entity.getBio(),
                entity.getCapacity() != null ? entity.getCapacity() : 0,
                entity.getAnimalsAvailable() != null ? entity.getAnimalsAvailable() : 0,
                entity.getHomeType(),
                entity.getPreviousExperience()
        );
    }
}
