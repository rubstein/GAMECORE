package gamecore.com.gamecore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Favorito;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.repository.ListaRepository;

@Service
public class ListaService {

    @Autowired
    private ListaRepository favoritoRepository;

    public boolean toggleFavorite(Usuario usuario, Videojuego videojuego) {
        if (isFavorite(usuario, videojuego)) {
            removeFavorite(usuario, videojuego);
            return false; 
        } else {
            addFavorite(usuario, videojuego);
            return true; 
        }
    }

    public boolean isFavorite(Usuario usuario, Videojuego videojuego) {
        return favoritoRepository.findByUsuarioAndVideojuego(usuario, videojuego) != null;
    }

    public void addFavorite(Usuario usuario, Videojuego videojuego) {
        Favorito favorito = new Favorito(usuario, videojuego);
        favoritoRepository.save(favorito);
    }

    public void removeFavorite(Usuario usuario, Videojuego videojuego) {
        Favorito favorito = favoritoRepository.findByUsuarioAndVideojuego(usuario, videojuego);
        favoritoRepository.delete(favorito);
    }

    public List<Favorito> r() {
        return favoritoRepository.findAll();
    }
}
