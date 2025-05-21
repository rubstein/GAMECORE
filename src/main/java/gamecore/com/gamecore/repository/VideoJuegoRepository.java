package gamecore.com.gamecore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface VideoJuegoRepository extends JpaRepository<Videojuego, Long> {
    public Videojuego findByNombre(String nombre);
    public Videojuego findBySlug(String slug);
    public List<Videojuego> findTop4ByOrderByFechaLanzamientoDesc();
}
