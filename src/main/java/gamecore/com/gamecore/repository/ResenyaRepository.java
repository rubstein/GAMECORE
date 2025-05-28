package gamecore.com.gamecore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Resenya;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface ResenyaRepository extends JpaRepository<Resenya, Long> {
    public Resenya findByUsuarioAndVideojuego(Usuario usuario, Videojuego videojuego);
    public List<Resenya> findByVideojuego(Videojuego videojuego);
    
}
