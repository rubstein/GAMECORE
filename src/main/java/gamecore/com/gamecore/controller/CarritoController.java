package gamecore.com.gamecore.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
    
    @GetMapping("/carrito")
    public String carrito(HttpSession session, ModelMap model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }
        Collection<Videojuego> videojuegosCarrito = usuario.getCarrito();
        model.put("videojuegos", videojuegosCarrito);
        model.put("view", "carrito/carrito");
        return "_t/frame";
    }
}
