package pe.edu.upc.patitasolidaria.backend.iam.domain.services;

import pe.edu.upc.patitasolidaria.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
