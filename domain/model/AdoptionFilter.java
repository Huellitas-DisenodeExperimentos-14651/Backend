package pe.edu.upc.patitasolidaria.backend.adoptions.domain.model;

import lombok.Data;

@Data
public class AdoptionFilter {
    private String gender;
    private String age;
    private String size;
    private String hair;
    private String activity;
    private String weight;
}