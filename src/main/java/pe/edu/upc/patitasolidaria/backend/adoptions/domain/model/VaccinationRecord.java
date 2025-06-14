package pe.edu.upc.patitasolidaria.backend.adoptions.domain.model;

import lombok.Data;
import java.util.List;

@Data
public class VaccinationRecord {
    private String date;
    private int doses;
    private List<String> vaccines;
}
