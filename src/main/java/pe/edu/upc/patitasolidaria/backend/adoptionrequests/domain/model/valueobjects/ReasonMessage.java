package pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor // ✅ Requerido por JPA para instanciar desde la base de datos
public class ReasonMessage {

    private String value;

    public ReasonMessage(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Reason message cannot be null or blank.");
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException("Reason message must be at most 500 characters.");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
