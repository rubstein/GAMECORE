package gamecore.com.gamecore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Puntuacion;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.repository.PuntuacionRepository;
import gamecore.com.gamecore.repository.VideoJuegoRepository;

@Service
public class PuntuacionService {

    @Autowired
    PuntuacionRepository puntuacionRepository;

    @Autowired
    VideoJuegoRepository videoJuegoRepository;

    public void guardarOActualizarPuntuacion(Usuario usuario, Videojuego juego, int valor) {

        Puntuacion puntuacion = puntuacionRepository.findByUsuarioAndVideojuego(usuario, juego);

        if (puntuacion == null) {
            puntuacion = new Puntuacion();
            puntuacion.setUsuario(usuario);
            puntuacion.setVideojuego(juego);
        }

        puntuacion.setValor(valor);
        puntuacionRepository.save(puntuacion);

        // Recalcular la puntuación media
        List<Puntuacion> puntuaciones = puntuacionRepository.findByVideojuego(juego);
        double media = puntuaciones.stream()
                .mapToInt(Puntuacion::getValor)
                .average()
                .orElse(0.0);

        juego.setPuntuacionMedia(media);
        videoJuegoRepository.save(juego);
    }

    public Puntuacion buscarPorUsuarioYVideojuego(Usuario usuario, Videojuego videojuego) {
        return puntuacionRepository.findByUsuarioAndVideojuego(usuario, videojuego);
    }

}
