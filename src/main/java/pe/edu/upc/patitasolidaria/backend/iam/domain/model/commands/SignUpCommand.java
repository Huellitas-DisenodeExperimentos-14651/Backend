package pe.edu.upc.patitasolidaria.backend.iam.domain.model.commands;

import pe.edu.upc.patitasolidaria.backend.iam.domain.model.entities.Role;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects.ProfileType;

import java.util.List;

public record SignUpCommand(
        String username,
        String password,
        List<Role> roles,
        CreateProfileCommand profileCommand
) {
    // Constructor adicional para compatibilidad sin perfil
    public SignUpCommand(String username, String password, List<Role> roles) {
        this(username, password, roles, null);
    }
}
