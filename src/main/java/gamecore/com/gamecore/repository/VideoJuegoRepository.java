package gamecore.com.gamecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface  VideoJuegoRepository extends JpaRepository<Videojuego, Long> {
}
