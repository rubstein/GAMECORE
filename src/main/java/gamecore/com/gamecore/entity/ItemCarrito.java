package gamecore.com.gamecore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Esto es clave
    @JoinColumn(name = "videojuego_id")
    private Videojuego videojuego;

    private int cantidad;

    public ItemCarrito(Videojuego videojuego, int cantidad) {
        this.videojuego = videojuego;
        this.cantidad = cantidad;
    }
}
