package pe.edu.upc.patitasolidaria.backend.pets.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.commands.CreatePetCommand;
import pe.edu.upc.patitasolidaria.backend.pets.domain.model.valueobjects.*;

import pe.edu.upc.patitasolidaria.backend.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.patitasolidaria.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "pets")
public class Pet extends AuditableAbstractAggregateRoot<Pet> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Getter
    @Min(0)
    @Column(name = "age")
    private Integer age;

    @Getter
    @Column(name = "photo", length = 300)
    private String photo;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "breed", length = 50))
    private Breed breed;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "size", length = 10)
    private PetSize size;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15)
    private PetStatus status;

    @Getter
    @Column(name = "description", length = 500)
    private String description;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "health_status", length = 100))
    private HealthStatus healthStatus;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "vaccination_status", length = 100))
    private VaccinationStatus vaccinationStatus;

    @Getter
    @Column(name = "special_needs", length = 300)
    private String specialNeeds;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    public Pet() {
        this.status = PetStatus.AVAILABLE;
    }

    public Pet(CreatePetCommand command, Profile profile) {
        this.name = command.name();
        this.age = command.age();
        this.photo = command.photo();
        this.breed = new Breed(command.breed());
        this.size = command.size();
        this.status = PetStatus.AVAILABLE;
        this.description = command.description();
        this.healthStatus = new HealthStatus(command.healthStatus());
        this.vaccinationStatus = new VaccinationStatus(command.vaccinationStatus());
        this.specialNeeds = command.specialNeeds();
        this.profile = profile;
    }

    public Pet updateInformation(
            String name,
            int age,
            String photo,
            String breed,
            PetSize size,
            PetStatus status,
            String description,
            String healthStatus,
            String vaccinationStatus,
            String specialNeeds
    ) {
        this.name = name;
        this.age = age;
        this.photo = photo;
        this.breed = new Breed(breed);
        this.size = size;
        this.status = status;
        this.description = description;
        this.healthStatus = new HealthStatus(healthStatus);
        this.vaccinationStatus = new VaccinationStatus(vaccinationStatus);
        this.specialNeeds = specialNeeds;
        return this;
    }

    public String getBreed() { return breed.value();}

    public String getHealthStatus() {return healthStatus.value();}

    public String getVaccinationStatus() {return vaccinationStatus.value();}

    public PetSize getSize() { return size; }
    public PetStatus getStatus() { return status; }
}