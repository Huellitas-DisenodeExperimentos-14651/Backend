package pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects.ReasonMessage;
import pe.edu.upc.patitasolidaria.backend.adoptionrequests.domain.model.valueobjects.RequestStatus;
import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.patitasolidaria.backend.publications.domain.model.aggregates.Publication;
import pe.edu.upc.patitasolidaria.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Entity
@Table(name = "adoption_requests")
@Getter
@Setter
@NoArgsConstructor
public class AdoptionRequest extends AuditableAbstractAggregateRoot<AdoptionRequest> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Profile applicant;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "reason_message", length = 500))
    private ReasonMessage reasonMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15)
    private RequestStatus status = RequestStatus.PENDING;

    @Column(name = "request_date")
    private LocalDate requestDate;

    public AdoptionRequest(Publication publication, Profile applicant, ReasonMessage reasonMessage) {
        this.publication = publication;
        this.applicant = applicant;
        this.reasonMessage = reasonMessage; // ✅ Guarda el value object correctamente
        this.status = RequestStatus.PENDING;
        this.requestDate = LocalDate.now();
    }

    public void approve() {
        if (this.status != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be approved.");
        }
        this.status = RequestStatus.APPROVED;
    }

    public void reject() {
        if (this.status != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be rejected.");
        }
        this.status = RequestStatus.REJECTED;
    }
}
