package gamecore.com.gamecore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.repository.GeneroRepository;
import gamecore.com.gamecore.repository.PlataformaRepository;
import gamecore.com.gamecore.repository.VideojuegoRepository;

@Service
public class VideojuegoService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public List<Videojuego> r() {
        return videojuegoRepository.findAll();
    }

    public void c(String nombre, String descripcion, String imagenUrl, LocalDate fechaLanzamiento,
            Double puntuacionMedia, String creadores, Double precio,List<Long> idsGeneros,List<Long> idsPlataformas)
            throws DangerException {
        Optional<Videojuego> videojuegoExistente = videojuegoRepository.findByNombre(nombre);
        if (videojuegoExistente.isPresent()) {
            throw new DangerException("El videojuego '" + nombre + "' ya está registrado.");
        }

        Videojuego vd = new Videojuego(nombre, descripcion, imagenUrl, fechaLanzamiento,puntuacionMedia,creadores, precio);

        for (Long idGenero : idsGeneros) {
            vd.getGeneros().add(generoRepository.findById(idGenero).get());
        }

        for (Long idPlataforma : idsPlataformas) {
            vd.getPlataformas().add(plataformaRepository.findById(idPlataforma).get());
        }

        videojuegoRepository.save(vd);
    }

    public Optional<Videojuego> obtenerPorNombre(String nombre) {
        return videojuegoRepository.findByNombre(nombre);
    }
}
