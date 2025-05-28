package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.service.ResenyaService;
import gamecore.com.gamecore.service.VideojuegoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/resenyas")
public class ResenyaController {
    
    @Autowired
    private ResenyaService resenyaService;

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping("guardar")
    public String guardarResenya(@RequestParam("slug") String slug,
            @RequestParam("contenido") String contenido,
            HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        Videojuego juego = videojuegoService.obtenerPorSlug(slug);
        if (juego == null) {
            return "redirect:/"; // o error
        }

        resenyaService.guardarOActualizarResenya(usuario, juego, contenido);
        return "redirect:/videojuego/" + slug;
    }
}
