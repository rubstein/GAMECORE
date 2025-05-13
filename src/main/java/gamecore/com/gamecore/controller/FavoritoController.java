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
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private VideoJuegoRepository videojuegoRepository;

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

    @GetMapping("/lista")
    public String verFavoritos(HttpSession session, ModelMap model) {
        // Obtener el usuario desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Verificar si el usuario está logueado
        if (usuario == null) {
            return "redirect:/usuario/login"; // Si no está logueado, redirigir a login
        }

        // Obtener la lista de videojuegos favoritos del usuario
        Collection<Videojuego> videojuegosFavoritos = usuario.getFavoritos();

        // Pasar la lista de videojuegos favoritos a la vista
        model.put("videojuegos", videojuegosFavoritos);

        // Establecer la vista
        model.put("view", "lista/r"); // Asegúrate de que la vista es correcta
        return "_t/frame"; // Retornar el frame principal donde se muestra la vista
    }

}