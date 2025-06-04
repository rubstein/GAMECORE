package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.service.VideojuegoService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private VideojuegoService videojuegoService;


    @GetMapping("/carrito")
    public String verCarrito(HttpSession session, ModelMap m) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        m.put("view", "carrito/carrito");

        return "_t/frame";
    }

    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam("slug") String slug, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        Videojuego videojuego = videojuegoService.obtenerPorSlug(slug);

        return "redirect:/carrito/carrito";
    }

}
