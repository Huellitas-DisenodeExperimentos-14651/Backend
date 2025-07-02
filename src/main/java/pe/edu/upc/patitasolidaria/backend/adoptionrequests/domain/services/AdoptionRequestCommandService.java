package pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.services;

import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.ApproveAdoptionRequestCommand;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.CreateAdoptionRequestCommand;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.commands.RejectAdoptionRequestCommand;

public interface AdoptionRequestCommandService {
    Long handle(CreateAdoptionRequestCommand command);
    void handle(ApproveAdoptionRequestCommand command);
    void handle(RejectAdoptionRequestCommand command);
}