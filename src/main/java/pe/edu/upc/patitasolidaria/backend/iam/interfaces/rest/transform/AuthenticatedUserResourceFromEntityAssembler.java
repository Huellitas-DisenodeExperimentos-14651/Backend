package pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates.User;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.aggregates.Profile;

public class AuthenticatedUserResourceFromEntityAssembler {

  public static AuthenticatedUserResource toResourceFromEntity(User user, Profile profile, String token) {
    return new AuthenticatedUserResource(
            user.getId(),
            user.getUsername(),
            profile != null ? profile.getId() : null,
            token
    );
  }
}
