package gamecore.com.gamecore.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Favorito;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface ListaRepository extends JpaRepository<Favorito, Long> {

    public Favorito findByUsuarioAndVideojuego(Usuario usuario, Videojuego videojuego);

    public List<Favorito> findByUsuario(Usuario usuario);

    public List<Favorito> findByVideojuego(Videojuego videojuego);
}
