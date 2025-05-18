package gamecore.com.gamecore.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Favorito;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    // Correcto: buscar si existe el favorito
    public Favorito findByUsuarioAndVideojuego(Usuario usuario, Videojuego videojuego);

    // Lista de favoritos de un usuario
    public List<Favorito> findByUsuario(Usuario usuario);

    // Lista de favoritos por videojuego (usuarios que lo tienen marcado)
    public List<Favorito> findByVideojuego(Videojuego videojuego);
}
