package gamecore.com.gamecore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    VideojuegoService videojuegoService;

    @Autowired
    PlataformaService plataformaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    GeneroService generoService;

    @Autowired
    RolService rolService;

    AdminController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("juegos")
    public String juegosR(
            ModelMap m) {
        m.put("videojuegos", videojuegoService.r());
        m.put("view", "/admin/juegos");
        return "_t/frame";
    }

    @GetMapping("juegos-c")
    public String cJ(
            ModelMap m) {
        m.put("view", "/admin/juegos-c");
        m.put("generos", generoService.r());
        m.put("plataformas", plataformaService.r());
        return "_t/frame";
    }

    @PostMapping("juegos-c")
    public String cPostJ(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("fechaLanzamiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLanzamiento,
            @RequestParam("creadores") String creadores,
            @RequestParam("precio") double precio,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("generos") List<Long> generosIds,
            @RequestParam("plataformas") List<Long> plataformasIds) throws DangerException {

        generosIds = generosIds == null ? new ArrayList<>() : generosIds;
        plataformasIds = plataformasIds == null ? new ArrayList<>() : plataformasIds;

        String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        Path rutaDestino = Paths.get("src/main/resources/static/img/", nombreArchivo);
        try {
            Files.createDirectories(rutaDestino.getParent());
            imagen.transferTo(rutaDestino);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c(nombre, descripcion, "/img/" + nombreArchivo, fechaLanzamiento, 0.0,
                    creadores, precio,
                    generosIds, plataformasIds);

        } catch (Exception e) {
            PRG.error("Este nombre " + nombre + " ya en uso", "/persona/c");
        }
        return "redirect:/admin/juegos";
    }

    @PostMapping("juegos-d")
    public String dJ(
            @RequestParam Long id) throws Exception {

        videojuegoService.d(id);

        return "redirect:/admin/juegos";
    }

    @GetMapping("usuarios")
    public String usuariosR(
            ModelMap m) {
        m.put("usuarios", usuarioService.r());
        m.put("view", "/admin/usuarios");
        return "_t/frame";
    }

    @GetMapping("usuarios-c")
    public String cU(
            ModelMap m) {
        m.put("view", "/admin/usuarios-c");
        m.put("roles", rolService.r());
        return "_t/frame";
    }

    
    @PostMapping("usuarios-c")
    public String cPostU(
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("contrasenya") String contrasenya,
            @RequestParam("email") String email,
            @RequestParam("rol") String rol) throws DangerException {

        try {

            usuarioService.validarUsuarioRegistro(nombreUsuario, contrasenya, email,rol);

        } catch (Exception e) {
            PRG.error("Este nombre " + nombreUsuario + " ya en uso", "/admin/usuarios");
        }
        return "redirect:/admin/usuarios";
    }
    
    @PostMapping("usuarios-d")
    public String dU(
            @RequestParam Long id) throws Exception {

        usuarioService.d(id);

        return "redirect:/admin/usuarios";
    }
}
