package gamecore.com.gamecore.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @JsonIgnore
    private String contrasenya;

    private String email;

    private LocalDate fecha;
    
    @ManyToOne
    private Rol rol;

    

    public Usuario(String nombreUsuario, String contrasenya, String email, LocalDate fecha) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenya = contrasenya;
        this.email = email;
        this.fecha = fecha;
    }

    public Usuario(String nombreUsuario, String contrasenya){
        this.nombreUsuario = nombreUsuario;
        this.contrasenya = contrasenya;
    }
}
