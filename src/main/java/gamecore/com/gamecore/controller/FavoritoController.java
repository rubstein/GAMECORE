package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.service.UsuarioService;
import gamecore.com.gamecore.service.VideojuegoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping("toggle")
    public String agregarAFavoritos(@RequestParam Long juegoId, HttpSession session, Model m) {

        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        Usuario usuario = usuarioService.findByIdConFavoritos(usuarioSesion.getId());
        Videojuego juego = videojuegoService.findById(juegoId);

        boolean enFavoritos;

        if (usuario.getFavoritos().contains(juego)) {
            usuarioService.eliminarDeFavoritos(usuario, juego);
            enFavoritos = false;
        } else {
            usuarioService.agregarAFavoritos(usuario, juego);
            enFavoritos = true;
        }

        m.addAttribute("videojuego", juego);
        m.addAttribute("enFavoritos", enFavoritos);
        m.addAttribute("view", "videojuego/review");
        return "_t/frame";
    }

}