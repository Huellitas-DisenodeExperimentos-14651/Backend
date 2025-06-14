package pe.edu.upc.patitasolidaria.backend.adoptions.domain.model;

import lombok.Data;
import java.util.List;

@Data
public class Adoption {
    private Long id;
    private String name;
    private Double age;
    private String species;
    private String breed;
    private String gender;
    private String location;
    private String imageUrl;
    private String size;
    private String hair;
    private String activity;
    private String weight;

    // Detalles adicionales
    private String description;
    private String rescueStory;
    private String rescuedBy;
    private String health;
    private Boolean vaccinated;
    private List<VaccinationRecord> vaccinationRecord;
}