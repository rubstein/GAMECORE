package gamecore.com.gamecore.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;
    private LocalDate anyo;
    private String estudio;
    private String Director;
    private String genero;
    private Double precio;
    private String plataforma;
    private String descripcion;
    private Double valoracion;

    public Videojuego(String Director, LocalDate anyo, String descripcion, String estudio, String genero, Long id, String nombre, String plataforma, Double precio, Double valoracion) {

        this.Director = Director;
        this.anyo = anyo;
        this.descripcion = descripcion;
        this.estudio = estudio;
        this.genero = genero;
        this.id = id;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.valoracion = valoracion;
    }
}
