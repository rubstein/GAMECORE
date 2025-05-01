package gamecore.com.gamecore.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.VideojuegoService;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/videojuego")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

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
            @RequestParam("puntuacionMedia") Double puntuacionMedia,
            @RequestParam("creadores") String creadores,
            @RequestParam("precio") Double precio,
            @RequestParam(value = "idGeneros[]", required = false) List<Long> idsGeneros,
            @RequestParam(value = "idPlataformas[]", required = false) List<Long> idsPlataformas)
            throws DangerException {

        idsGeneros = idsGeneros == null ? new ArrayList<>() : idsGeneros;
        idsPlataformas = idsPlataformas == null ? new ArrayList<>() : idsPlataformas;

        try {
            this.videojuegoService.c(nombre, descripcion, imagenUrl, fechaLanzamiento, puntuacionMedia, creadores,
                    precio,
                    idsGeneros, idsPlataformas);
        } catch (Exception e) {
            PRG.error("El juego" + nombre + " ya está registrado", "/videojuego/c");
        }
        return "redirect:/videojuego/r";
    }

    @GetMapping("/videojuego/{nombre}")
    public String obtenerVideojuegoPorNombre(@PathVariable String nombre, Model m) {
        Optional<Videojuego> optionalVideojuego = videojuegoService.obtenerPorNombre(nombre);
        if (optionalVideojuego.isPresent()) {
            m.addAttribute("videojuego", optionalVideojuego.get());
            return "videojuego/review"; 
        } else {
            return "error/404"; 
        }
    }
}
