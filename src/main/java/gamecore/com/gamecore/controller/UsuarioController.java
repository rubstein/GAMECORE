package gamecore.com.gamecore.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.EmailService;
import gamecore.com.gamecore.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/registro")
    public String registro(ModelMap m, HttpSession hs) {

        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put("usuario", u);
        m.put("view", "usuario/registro");
        return "_t/frame";
    }

    @GetMapping("/terminos")
    public String terminos(ModelMap m) {
        m.put("view", "usuario/terminos");
        return "_t/frame";
    }

    @PostMapping("/registro")
    @ResponseBody
    public Map<String, Object> registroPost(@RequestParam String nuevoUsuario,
            @RequestParam String nuevaContrasenya,
            @RequestParam String nuevoEmail,
            HttpSession s) throws DangerException {
        Map<String, Object> response = new HashMap<>();
        System.out.println("Datos recibidos");

        try {

            if (usuarioService.existsByName(nuevoUsuario)) {
                response.put("success", false);
                response.put("message", "El nombre de usuario ya está en uso");
                return response;
            }

            if (usuarioService.existsByEmail(nuevoEmail)) {
                response.put("success", false);
                response.put("message", "El email introducido ya está en uso");
                return response;
            }

            Usuario u = this.usuarioService.validarUsuarioRegistro(nuevoUsuario, nuevaContrasenya, nuevoEmail, "user");
            s.setAttribute("usuario", u);

            response.put("success", true);
            response.put("message", "Registro correcto");

            emailService.enviarEmailRegistro(nuevoEmail, nuevoUsuario);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al registrar el usuario: " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/login")
    public String login(ModelMap m, HttpSession hs) {
        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put("usuario", u);
        m.put("view", "usuario/login");
        return "_t/frame";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> loginPost(@RequestParam String usuario,
            @RequestParam String contrasenya,
            HttpSession s) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario u = usuarioService.usuarioLogin(usuario, contrasenya);
            s.setAttribute("usuario", u);
            response.put("success", true);
            response.put("message", "Login exitoso");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Usuario o contraseña incorrectos");
        }
        return response;
    }

    @GetMapping("/logout")
    public String logout(HttpSession s) {
        s.invalidate();
        return "redirect:/";
    }

    @GetMapping("/perfil")
    public String perfil(ModelMap m, HttpSession hs) {
        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put("usuario", u);
        m.put("view", "usuario/perfil");
        return "_t/frame";
    }

    @PostMapping("/perfil")
    @ResponseBody
    public Map<String, Object> perfilPost(@RequestParam String nuevoNombreUsuario,
            @RequestParam String nuevoEmail,
            HttpSession s) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario u = (Usuario) s.getAttribute("usuario");
            Usuario usuarioPorNombre = usuarioService.findByNombreUsuario(nuevoNombreUsuario);
            if (usuarioPorNombre != null && !usuarioPorNombre.getId().equals(u.getId())) {
                response.put("success", false);
                response.put("message", "El nombre de usuario ya está en uso.");
                return response;
            }

            Usuario usuarioPorEmail = usuarioService.findByEmail(nuevoEmail);
            if (usuarioPorEmail != null && !usuarioPorEmail.getId().equals(u.getId())) {
                response.put("success", false);
                response.put("message", "El correo electrónico ya está en uso.");
                return response;
            }

            usuarioService.u(u.getId(), nuevoNombreUsuario, nuevoEmail);
            Usuario usuarioActualizado = usuarioService.findById(u.getId());
            s.setAttribute("usuario", usuarioActualizado);

            response.put("success", true);
            response.put("message", "Datos guardados correctamente.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error al actualizar el perfil.");
        }
        return response;
    }

    @GetMapping("/check")
    @ResponseBody
    public Map<String, Object> checkUsuarioEmail(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String email) {
        Map<String, Object> response = new HashMap<>();
        if (usuario != null && usuarioService.existsByName(usuario)) {
            response.put("usuario", true);
        }
        if (email != null && usuarioService.existsByEmail(email)) {
            response.put("email", true);
        }
        return response;
    }
}
