package gamecore.com.gamecore.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.UsuarioService;
import gamecore.com.gamecore.service.VideojuegoService;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/videojuego")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/r")
    public String r(
            ModelMap m) {
        m.put("videojuegos", videojuegoService.r());
        m.put("view", "videojuego/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(
            ModelMap m) {
        m.put("view", "videojuego/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagenUrl") String imagenUrl,
            @RequestParam("fechaLanzamiento") LocalDate fechaLanzamiento,
            @RequestParam("creadores") String creadores,
            @RequestParam("precio") Double precio,
            @RequestParam(value = "idGeneros[]", required = false) List<Long> idsGeneros,
            @RequestParam(value = "idPlataformas[]", required = false) List<Long> idsPlataformas)
            throws DangerException {

        idsGeneros = idsGeneros == null ? new ArrayList<>() : idsGeneros;
        idsPlataformas = idsPlataformas == null ? new ArrayList<>() : idsPlataformas;

        try {
            this.videojuegoService.c(nombre, descripcion, imagenUrl, fechaLanzamiento, 0.0, creadores,
                    precio,
                    idsGeneros, idsPlataformas);
        } catch (Exception e) {
            PRG.error("El juego" + nombre + " ya está registrado", "/videojuego/c");
        }
        return "redirect:/videojuego/r";
    }

    @GetMapping("/{slug}")
    public String obtenerVideojuegoPorSlug(@PathVariable String slug, Model m, HttpSession session) {
        Videojuego videojuego = videojuegoService.obtenerPorSlug(slug);
        if (videojuego != null) {
            m.addAttribute("videojuego", videojuego);

            // NUEVO: Comprobar si usuario está logueado y si el juego está en favoritos
            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
            boolean enFavoritos = false;
            if (usuarioSesion != null) {
                Usuario usuario = usuarioService.findByIdConFavoritos(usuarioSesion.getId());
                enFavoritos = usuario.getFavoritos().contains(videojuego);
            }
            m.addAttribute("enFavoritos", enFavoritos);

            m.addAttribute("view", "videojuego/review");
            return "_t/frame";
        } else {
            return "error/404";
        }
    }

}
