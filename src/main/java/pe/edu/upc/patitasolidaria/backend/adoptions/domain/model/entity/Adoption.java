package pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.upc.patitasolidaria.backend.adoptions.domain.model.valueobject.VaccinationRecord;

@Entity
@Data
@Table(name = "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String description;
    private String rescueStory;
    private String rescuedBy;
    private String health;
    private Boolean vaccinated;

    @Embedded
    private VaccinationRecord vaccinationRecord;
}