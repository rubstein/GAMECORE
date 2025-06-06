package gamecore.com.gamecore.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.repository.VideoJuegoRepository;
import gamecore.com.gamecore.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/lista")
public class ListaController {

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/toggle")
    public String toggleFavorito(@RequestParam Long juegoId,
            @RequestParam String slug,
            HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        Videojuego juego = videojuegoRepository.findById(juegoId).orElse(null);
        if (juego == null) {
            return "redirect:/";
        }

        return "redirect:/videojuego/" + slug;
    }

    @GetMapping("/favorito")
    public String verFavoritos(HttpSession session, ModelMap model) {
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");

        if (usuarioSesion == null) {
            return "redirect:/usuario/login";
        }

        // Recuperar el usuario con favoritos cargados desde BD
        Usuario usuario = usuarioService.findByIdConFavoritos(usuarioSesion.getId());

        Collection<Videojuego> videojuegosFavoritos = usuario.getFavoritos();
        model.put("videojuegos", videojuegosFavoritos);
        model.put("view", "lista/favorito");
        return "_t/frame";
    }

    

}