package gamecore.com.gamecore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface VideoJuegoRepository extends JpaRepository<Videojuego, Long> {
    public Videojuego findByNombre(String nombre);

    public Videojuego findBySlug(String slug);

    public List<Videojuego> findTop4ByOrderByFechaLanzamientoDesc();

    @Query("SELECT v FROM Videojuego v JOIN v.generos g WHERE g.nombre = :nombreGenero")
    public List<Videojuego> findByGeneroNombre(@Param("nombreGenero") String nombreGenero);

    @Query("SELECT v FROM Videojuego v JOIN v.plataformas p WHERE p.nombre = :nombrePlataforma")
    public List<Videojuego> findByPlataformasNombre(@Param("nombrePlataforma") String nombrePlataforma);

    public List<Videojuego> findByNombreContainingIgnoreCase(String nombre);
}
