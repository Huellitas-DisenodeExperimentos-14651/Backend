package pe.edu.upc.patitasolidaria.backend.profiles.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects.ProfileType;
import pe.edu.upc.patitasolidaria.backend.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.name(),
                resource.email(),
                resource.address(),
                ProfileType.valueOf(resource.role().toUpperCase()),
                resource.paymentMethods(),
                resource.preferences(),
                resource.profilePic(),
                resource.bio(),
                resource.capacity(),
                resource.animalsAvailable(), // ✅ Ahora es int
                resource.homeType(),
                resource.previousExperience()
        );
    }
}
