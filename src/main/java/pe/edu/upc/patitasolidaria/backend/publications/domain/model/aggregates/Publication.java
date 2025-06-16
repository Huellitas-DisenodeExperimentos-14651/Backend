package pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upc.patitasolidaria.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.valueobjects.ContactInfo;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.valueobjects.Location;

import java.time.LocalDateTime;

@Entity
@Table(name = "publications")
@NoArgsConstructor
public class Publication extends AuditableAbstractAggregateRoot<Publication> {

    @Getter
    @Column(nullable = false, length = 100)
    private String title;

    @Getter
    @Column(nullable = false, length = 1000)
    private String description;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "contact_info"))
    private ContactInfo contactInfo;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "location"))
    private Location location;

    @Getter
    @Column(nullable = false)
    private LocalDateTime publishedAt;

    @Getter
    @Column(nullable = false)
    private Long petId; // relación indirecta con Pets BC

    @Getter
    private boolean isActive;

    public Publication(String title, String description, ContactInfo contactInfo, Location location, Long petId) {
        this.title = title;
        this.description = description;
        this.contactInfo = contactInfo;
        this.location = location;
        this.publishedAt = LocalDateTime.now();
        this.petId = petId;
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void update(String title, String description, ContactInfo contactInfo, Location location) {
        this.title = title;
        this.description = description;
        this.contactInfo = contactInfo;
        this.location = location;
    }
}
