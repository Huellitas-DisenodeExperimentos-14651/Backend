package pe.edu.upc.patitasolidaria.backend.profiles.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.commands.UpdateProfileCommand;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects.ProfileType;
import pe.edu.upc.patitasolidaria.backend.profiles.interfaces.rest.resources.ProfileResource;

import java.util.List;

public class UpdateProfileCommandFromResourceAssembler {

    public static UpdateProfileCommand toCommandFromResource(Long profileId, ProfileResource resource) {
        return new UpdateProfileCommand(
                profileId,
                resource.name(),
                resource.email(),
                resource.address(),
                ProfileType.valueOf(resource.role().toUpperCase()),
                resource.paymentMethods(),
                resource.preferences(),
                resource.profilePic(),
                resource.bio(),
                resource.capacity(),
                resource.animalsAvailable(),
                resource.homeType(),
                resource.previousExperience()
        );
    }
}