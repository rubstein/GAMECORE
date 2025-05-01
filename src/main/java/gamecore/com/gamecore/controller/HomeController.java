package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.service.GeneroService;
import gamecore.com.gamecore.service.VideojuegoService;

import java.util.List;
import java.util.stream.Collectors;



import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private GeneroService generoService;

    @GetMapping("/")
    public String home(ModelMap m, HttpSession hs) {
        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put("usuario", u);
        m.put("view", "/home/home");
        m.put("videojuegos", videojuegoService.r());
        m.addAttribute("generos", generoService.r());
        List<Videojuego> videojuegos = videojuegoService.r();
        List<Videojuego> topVideojuegos = videojuegos.stream()
            .sorted((v1, v2) -> Double.compare(v2.getPuntuacionMedia(), v1.getPuntuacionMedia()))
            .limit(9) // Obtener solo los 9 primeros
            .collect(Collectors.toList());
            List<Videojuego> grupo1 = topVideojuegos.subList(0, 3);
            List<Videojuego> grupo2 = topVideojuegos.subList(3, 6);
            List<Videojuego> grupo3 = topVideojuegos.subList(6, 9);
    
            // Agregar los grupos al modelo
            m.addAttribute("grupo1", grupo1);
            m.addAttribute("grupo2", grupo2);
            m.addAttribute("grupo3", grupo3);
        return "_t/frame";
    }

    @PostMapping("/")
    public String registro(ModelMap m) {
        m.put("view", "/home/home");
        return "_t/frame";
    }

}