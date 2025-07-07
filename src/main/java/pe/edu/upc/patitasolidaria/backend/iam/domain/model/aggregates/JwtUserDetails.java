package pe.edu.upc.patitasolidaria.backend.iam.domain.model.aggregates;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Long profileId;
    private final String role;


    public JwtUserDetails(String username, String password, Long profileId, String role) {
        this.username = username;
        this.password = password;
        this.profileId = profileId;
        this.role = role;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // o devuelve roles si los tienes
    }

    @Override
    public String getPassword() {
        return password; // puede ser null si no te importa
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
