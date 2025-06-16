package pe.edu.upc.patitasolidaria.backend.profiles.domain.model.valueobjects;

public enum ProfileType {
    ADOPTER("Adoptante"),
    SHELTER("Refugio"),
    ADMIN("Administrador");

    private final String displayName;

    ProfileType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
