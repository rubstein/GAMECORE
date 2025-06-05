package gamecore.com.gamecore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.repository.GeneroRepository;
import gamecore.com.gamecore.repository.PlataformaRepository;
import gamecore.com.gamecore.repository.PuntuacionRepository;
import gamecore.com.gamecore.repository.VideoJuegoRepository;
import jakarta.transaction.Transactional;

@Service
public class VideojuegoService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    public List<Videojuego> r() {
        return videojuegoRepository.findAll();
    }

    public void c(String nombre, String descripcion, String nombreArchivoImagen, LocalDate fechaLanzamiento,
            Double puntuacionMedia, String creadores, Double precio, List<Long> idsGeneros, List<Long> idsPlataformas)
            throws DangerException {

        Videojuego videojuegoExistente = videojuegoRepository.findByNombre(nombre);
        if (videojuegoExistente != null) {
            throw new DangerException("El videojuego '" + nombre + "' ya está registrado.");
        }

        // Aquí pasamos el nombreArchivoImagen directamente (puede ser null si no se
        // subió imagen)
        Videojuego vd = new Videojuego(nombre, descripcion, nombreArchivoImagen, fechaLanzamiento, puntuacionMedia,
                creadores,
                precio);

        // Generar slug (como ya tienes)
        String slug = nombre.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("-", " ")
                .replaceAll("\\s+", "-");

        vd.setSlug(slug);

        for (Long idGenero : idsGeneros) {
            generoRepository.findById(idGenero).ifPresent(vd.getGeneros()::add);
        }

        for (Long idPlataforma : idsPlataformas) {
            plataformaRepository.findById(idPlataforma).ifPresent(vd.getPlataformas()::add);
        }

        videojuegoRepository.save(vd);
    }

    @Transactional
    public void d(Long id) throws Exception {
        puntuacionRepository.deleteByVideojuegoId(id);
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

    public List<Videojuego> obtenerPorPlataforma(String nombrePlatafroma) {
        return videojuegoRepository.findByPlataformasNombre(nombrePlatafroma);
    }

    public Videojuego save(Videojuego videojuego) {

        return videojuegoRepository.save(videojuego);
    }
}
