package gamecore.com.gamecore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import gamecore.com.gamecore.entity.Puntuacion;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    public Puntuacion findByUsuarioAndVideojuego(Usuario usuario, Videojuego videojuego);
    public List<Puntuacion> findByVideojuego(Videojuego videojuego);
}
