package pe.edu.upc.patitasolidaria.backend.manageadoptions.domain.model.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class Applicant {

    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String dni;
    private String location;
    private String maritalStatus;
    private Boolean hasChildren;
    private Boolean livesWithParents;
    private String economicStatus;
    private String phoneNumber;
    private String petExperience;
}