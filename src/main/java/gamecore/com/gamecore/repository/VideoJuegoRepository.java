package gamecore.com.gamecore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
    Optional<Videojuego> findByNombre(String nombre);
}
