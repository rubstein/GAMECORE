package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.service.GeneroService;
import gamecore.com.gamecore.service.PlataformaService;
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
    
    @Autowired
    private PlataformaService plataformasService;

    @GetMapping("/")
    public String home(ModelMap m, HttpSession hs) {
        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put("usuario", u);
        m.put("view", "/home/home");
        m.put("videojuegos", videojuegoService.r());
        m.addAttribute("generos", generoService.r());
        m.addAttribute("plataformas", plataformasService.r());
        List<Videojuego> videojuegos = videojuegoService.r();
        List<Videojuego> topVideojuegos = videojuegos.stream()
                .sorted((v1, v2) -> Double.compare(v2.getPuntuacionMedia(), v1.getPuntuacionMedia()))
                .limit(9) // Obtener solo los 9 primeros
                .collect(Collectors.toList());
        List<Videojuego> grupo1 = topVideojuegos.subList(0, 3);
        List<Videojuego> grupo2 = topVideojuegos.subList(3, 6);
        List<Videojuego> grupo3 = topVideojuegos.subList(6, 9);
        List<Videojuego> news = videojuegoService.obtenerCuatroVideojuegos();

        m.addAttribute("news", news);

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

    @GetMapping("/admin/editar-card/{id}")
    public String mostrarFormularioCambioCard(@PathVariable Long id, ModelMap m) {
        // Obtener el videojuego actual
        Videojuego videojuegoActual = videojuegoService.obtenerPorId(id);
        // Obtener todos los videojuegos disponibles
        List<Videojuego> todosLosVideojuegos = videojuegoService.r();

        m.put("view", "/admin/editar-card");
        // Añadir la lista de juegos y el videojuego actual al modelo
        m.addAttribute("videojuegoActual", videojuegoActual);
        m.addAttribute("todosLosVideojuegos", todosLosVideojuegos);

        return "_t/frame"; // Retorna la vista donde el administrador podrá seleccionar el nuevo videojuego
    }

    @PostMapping("/admin/editar-card/{id}")
    public String cambiarCard(@PathVariable Long id, @RequestParam Long nuevoId) {
        // Obtener el videojuego actual
        Videojuego videojuegoActual = videojuegoService.obtenerPorId(id);
        // Obtener el nuevo videojuego a reemplazar
        Videojuego nuevoVideojuego = videojuegoService.obtenerPorId(nuevoId);

        // Actualiza la tarjeta actual con el nuevo videojuego
        videojuegoService.cambiarCard(videojuegoActual, nuevoVideojuego);

        return "redirect:/"; // Redirige a la página principal después de cambiar la tarjeta
    }
}