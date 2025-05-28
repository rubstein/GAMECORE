package gamecore.com.gamecore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Resenya;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.repository.ResenyaRepository;

@Service
public class ResenyaService {
    
    @Autowired
    private ResenyaRepository resenyaRepository;

    public void guardarOActualizarResenya(Usuario usuario, Videojuego juego, String contenido) {
        Resenya resenya = resenyaRepository.findByUsuarioAndVideojuego(usuario, juego);

        if (resenya == null) {
            resenya = new Resenya();
            resenya.setUsuario(usuario);
            resenya.setVideojuego(juego);
        }

        resenya.setContenido(contenido);
        resenyaRepository.save(resenya);
    }

     public List<Resenya> obtenerPorVideojuego(Videojuego videojuego) {
        return resenyaRepository.findByVideojuego(videojuego);
    }
}
