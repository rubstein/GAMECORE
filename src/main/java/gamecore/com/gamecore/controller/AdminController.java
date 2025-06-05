package gamecore.com.gamecore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import gamecore.com.gamecore.entity.Genero;
import gamecore.com.gamecore.entity.Plataforma;
import gamecore.com.gamecore.entity.Rol;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
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

    @Autowired
    private EmailService emailService;

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

        String nombreArchivo = null;
        if (imagen != null && !imagen.isEmpty()) {
            nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();

            // Ruta absoluta donde guardar la imagen (fuera de src/main/resources)
            Path rutaDestino = Paths.get("/uploads/img/", nombreArchivo);
            try {
                Files.createDirectories(rutaDestino.getParent());
                imagen.transferTo(rutaDestino.toFile());
            } catch (IOException e) {
                e.printStackTrace();
                // Aquí podrías manejar la excepción o lanzar otra
            }
        }

        try {
            videojuegoService.c(nombre, descripcion, nombreArchivo, fechaLanzamiento, 0.0,
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
    @ResponseBody
    public Map<String, Object> cPostU(
            @RequestParam("nombreUsuario") String nombreUsuario,
            @RequestParam("contrasenya") String contrasenya,
            @RequestParam("email") String email,
            @RequestParam("rol") String rol) {

        Map<String, Object> response = new HashMap<>();
        try {

            if (usuarioService.existsByName(nombreUsuario)) {
                response.put("success", false);
                response.put("message", "El nombre de usuario ya está en uso.");
                return response;
            }

            if (usuarioService.existsByEmail(email)) {
                response.put("success", false);
                response.put("message", "El email ya está en uso.");
                return response;
            }
            usuarioService.validarUsuarioRegistro(nombreUsuario, contrasenya, email, rol);
            response.put("success", true);
            response.put("message", "Usuario creado correctamente.");
            emailService.enviarEmailRegistro(email, nombreUsuario);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al crear el usuario.");
        }
        return response;
    }

    @PostMapping("usuarios-d")
    public String dU(
            @RequestParam Long id) throws Exception {

        usuarioService.d(id);

        return "redirect:/admin/usuarios";
    }

    @GetMapping("usuarios-u")
    public String mostrarFormularioEditarRoles(@RequestParam("id") Long id, ModelMap m) throws Exception {
        Usuario usuario = usuarioService.findById(id);
        List<Rol> roles = rolService.r();

        m.addAttribute("usuario", usuario);
        m.addAttribute("roles", roles);
        m.addAttribute("view", "/admin/usuarios-u");

        return "_t/frame";
    }

    @PostMapping("usuarios-u")
    public String actualizarUsuario(
            @RequestParam Long id,
            @RequestParam String rol) throws Exception {

        Usuario usuario = usuarioService.findById(id);
        Rol nuevoRol = rolService.findById(Long.parseLong(rol));
        Set<Rol> nuevosRoles = new HashSet<>();
        nuevosRoles.add(nuevoRol);
        usuario.setRoles(nuevosRoles);
        usuarioService.save(usuario);

        return "redirect:/admin/usuarios";
    }

    @GetMapping("juegos-u")
    public String mostrarEditorJuegos(@RequestParam("id") Long id, ModelMap m) throws Exception {
        Videojuego videojuego = videojuegoService.findById(id);
        Collection<Plataforma> plataformas = plataformaService.r();
        Collection<Genero> generos = generoService.r();

        m.addAttribute("videojuego", videojuego);
        m.addAttribute("plataformas", plataformas);
        m.addAttribute("generos", generos);

        m.addAttribute("view", "/admin/juegos-u");

        return "_t/frame";
    }

    @PostMapping("juegos-u")
    public String actualizarJuego(
            @RequestParam Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("fechaLanzamiento") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaLanzamiento,
            @RequestParam("creadores") String creadores,
            @RequestParam("precio") double precio,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("generos") List<Long> generosIds,
            @RequestParam("plataformas") List<Long> plataformasIds) throws Exception {

        Videojuego videojuego = videojuegoService.findById(id);

        if (videojuego == null) {
            throw new Exception("Videojuego no encontrado con ID: " + id);
        }

        videojuego.setNombre(nombre);
        videojuego.setDescripcion(descripcion);
        videojuego.setFechaLanzamiento(fechaLanzamiento);
        videojuego.setCreadores(creadores);
        videojuego.setPrecio(precio);

        if (!imagen.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();

            // Carpeta relativa externa
            Path rutaArchivo = Paths.get("uploads/img/" + nombreArchivo);

            // Asegúrate de que exista la carpeta (por si acaso)
            Files.createDirectories(rutaArchivo.getParent());

            try {
                Files.copy(imagen.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);
                videojuego.setImagenUrl(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Actualizamos los géneros
        List<Genero> generos = (generosIds != null)
                ? new ArrayList<>(generosIds.stream().map(generoService::findById).toList())
                : new ArrayList<>();
        videojuego.setGeneros(generos);

        // Actualizamos las plataformas
        List<Plataforma> plataformas = (plataformasIds != null)
                ? new ArrayList<>(plataformasIds.stream().map(plataformaService::findById).toList())
                : new ArrayList<>();
        videojuego.setPlataformas(plataformas);

        videojuegoService.save(videojuego);

        return "redirect:/admin/juegos";
    }

}
