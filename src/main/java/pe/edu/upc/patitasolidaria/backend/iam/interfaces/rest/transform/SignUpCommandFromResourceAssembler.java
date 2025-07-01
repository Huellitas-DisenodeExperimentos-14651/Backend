package pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.transform;

import pe.edu.upc.patitasolidaria.backend.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.patitasolidaria.backend.iam.domain.model.entities.Role;
import pe.edu.upc.patitasolidaria.backend.iam.interfaces.rest.resources.SignUpResource;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects.ProfileType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignUpCommandFromResourceAssembler {

  public static SignUpCommand toCommandFromResource(SignUpResource resource) {
    List<Role> roles;

    if (resource.roles() == null || resource.roles().isBlank()) {
      // Si no se especifican roles, asignar rol por defecto
      roles = List.of(Role.getDefaultRole());
    } else {
      // Si roles tiene valor, puede ser una cadena con roles separados por coma
      roles = Stream.of(resource.roles().split(","))
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(Role::toRoleFromName)
              .collect(Collectors.toList());
    }

    var profileCommand = new CreateProfileCommand(
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

    return new SignUpCommand(resource.username(), resource.password(), roles, profileCommand);
  }
}
