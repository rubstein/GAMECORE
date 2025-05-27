package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.service.PuntuacionService;
import gamecore.com.gamecore.service.VideojuegoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/puntuaciones")
public class PuntuacionController {

    @Autowired
    private PuntuacionService puntuacionService;

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping("/agregar")
    public String agregarPuntuacion(
            @RequestParam("slug") String slug,
            @RequestParam("valor") int valor,
            HttpSession session) throws DangerException {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        Videojuego videojuego = videojuegoService.obtenerPorSlug(slug);
        if (videojuego == null) {
            throw new DangerException("El videojuego no existe.");
        }

        puntuacionService.guardarOActualizarPuntuacion(usuario, videojuego, valor);

        session.setAttribute("usuario", usuario);

        return "redirect:/videojuego/" + slug;
    }
}
