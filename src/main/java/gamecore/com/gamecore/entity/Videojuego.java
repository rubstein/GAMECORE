package gamecore.com.gamecore.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    private String imagenUrl;

    private LocalDate fechaLanzamiento;

    private Double puntuacionMedia;

    private String creadores;

    private Double precio;

    private String slug;

    @ManyToMany
    private Collection<Genero> generos;

    @ManyToMany
    private Collection<Plataforma> plataformas;

    public Videojuego(String nombre, String descripcion, String imagenUrl, LocalDate fechaLanzamiento,
            Double puntuacionMedia, String creadores, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.fechaLanzamiento = fechaLanzamiento;
        this.puntuacionMedia = puntuacionMedia;
        this.creadores = creadores;
        this.precio = precio;
        this.generos = new ArrayList<>();
        this.plataformas = new ArrayList<>();
    }

    public String getColorPorPuntuacion() {
        if (this.puntuacionMedia >= 7.0) {
            return "color-verde";
        } else if (this.puntuacionMedia >= 5.0) {
            return "color-naranja";
        } else {
            return "color-rojo";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Videojuego juego = (Videojuego) o;
        return id != null && id.equals(juego.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
