package gamecore.com.gamecore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.repository.GeneroRepository;
import gamecore.com.gamecore.repository.PlataformaRepository;
import gamecore.com.gamecore.repository.VideoJuegoRepository;

@Service
public class VideojuegoService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

    public List<Videojuego> r() {
        return videojuegoRepository.findAll();
    }

    public void c(String nombre, String descripcion, String imagenUrl, LocalDate fechaLanzamiento,
            Double puntuacionMedia, String creadores, Double precio, List<Long> idsGeneros, List<Long> idsPlataformas)
            throws DangerException {
        Videojuego videojuegoExistente = videojuegoRepository.findByNombre(nombre);
        if (videojuegoExistente != null) {
            throw new DangerException("El videojuego '" + nombre + "' ya está registrado.");
        }

        Videojuego vd = new Videojuego(nombre, descripcion, imagenUrl, fechaLanzamiento, puntuacionMedia, creadores,
                precio);

        String slug = nombre.toLowerCase()
                .replaceAll("[^a-z0-9\s]", "")
                .replaceAll("-", " ")
                .replaceAll("\s+", "-");

        vd.setSlug(slug);

        for (Long idGenero : idsGeneros) {
            vd.getGeneros().add(generoRepository.findById(idGenero).get());
        }

        for (Long idPlataforma : idsPlataformas) {
            vd.getPlataformas().add(plataformaRepository.findById(idPlataforma).get());
        }

        videojuegoRepository.save(vd);
    }

    public void d(Long id) throws Exception {
        videojuegoRepository.deleteById(id);
    }

    public Videojuego obtenerPorNombre(String nombre) {
        return videojuegoRepository.findByNombre(nombre);
    }

    public Videojuego obtenerPorSlug(String slug) {
        return videojuegoRepository.findBySlug(slug);
    }

    public List<Videojuego> obtenerCuatroVideojuegos() {
        return videojuegoRepository.findTop4ByOrderByFechaLanzamientoDesc();
    }

    // Obtener un videojuego por ID
    public Videojuego obtenerPorId(Long id) {
        return videojuegoRepository.findById(id).orElse(null);
    }

    // Cambiar un videojuego por otro en una tarjeta
    public void cambiarCard(Videojuego videojuegoActual, Videojuego nuevoVideojuego) {
        // Reemplaza el videojuego actual con el nuevo en la base de datos
        videojuegoActual.setImagenUrl(nuevoVideojuego.getImagenUrl());
        videojuegoActual.setNombre(nuevoVideojuego.getNombre());
        videojuegoActual.setPuntuacionMedia(nuevoVideojuego.getPuntuacionMedia());
        videojuegoRepository.save(videojuegoActual);
    }

    public Videojuego findById(Long juegoId) {
        return videojuegoRepository.findById(juegoId)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado con ID: " + juegoId));
    }

    public List<Videojuego> obtenerPorGenero(String nombreGenero) {
        return videojuegoRepository.findByGeneroNombre(nombreGenero);
    }
}
