package gamecore.com.gamecore.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombreUsuario;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles;

    @JsonIgnore
    private String contrasenya;

    private String email;

    @ManyToMany
    private Set<Videojuego> favoritos;

    public Usuario(String nombreUsuario, String contrasenya, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenya = contrasenya;
        this.email = email;
        this.roles = new ArrayList<>();
        this.favoritos = new HashSet<Videojuego>();
    }

    public Usuario(String nombreUsuario, String contrasenya) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenya = contrasenya;
    }

    public boolean isAdmin() {
    if (roles == null) return false;
    for (Rol rol : roles) {
        if ("admin".equalsIgnoreCase(rol.getNombre())) {
            return true;
        }
    }
    return false;
}
}
