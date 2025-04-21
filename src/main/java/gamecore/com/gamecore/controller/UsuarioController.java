package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

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
    public String registroPost(@RequestParam String nuevoUsuario, 
                            @RequestParam String nuevaContrasenya,
                            @RequestParam String nuevoEmail,
                            @RequestParam String nuevaEdad,
                            HttpSession s) throws DangerException {
                                System.out.println("Datos recibidos");
                                try 
                                {
                                    Usuario u = this.usuarioService.validarUsuarioRegistro(nuevoUsuario, nuevaContrasenya, nuevoEmail, nuevaEdad);
                                    s.setAttribute("usuario", u);
                                }
                                catch (Exception e) 
                                {
                                    PRG.error("Error al registrar el usuario", "/usuario/registro");
                                }
                        
                                return "redirect:/";
}

    @GetMapping("/login")
    public String login(ModelMap m, HttpSession hs) 
    {
        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put ("usuario", u);
        m.put("view", "usuario/login");
        return "_t/frame";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String usuario, 
                            @RequestParam String contrasenya, 
                            HttpSession s) throws DangerException {

        try{
            Usuario u = usuarioService.validarUsuarioLogin(usuario, contrasenya); 
            s.setAttribute("usuario", u);
            
        } catch (Exception e){
            PRG.error("Usuario o contraseña incorrectos", "/usuario/login");
        }
            return "redirect:/";
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
    public String perfilPost(@RequestParam String nuevoNombreUsuario,
                             @RequestParam String nuevoEmail,
                             HttpSession s) throws DangerException {
        try {
            Usuario usuarioActual = (Usuario) s.getAttribute("usuario");
            if (usuarioActual == null) {
                throw new DangerException("No hay un usuario autenticado.");
            }
            Usuario usuarioActualizado = usuarioService.u(usuarioActual.getId(), nuevoNombreUsuario, nuevoEmail);

            s.setAttribute("usuario", usuarioActualizado);

            return "redirect:/usuario/perfil";
        } catch (Exception e) {
            PRG.error("Error al actualizar el perfil: " + e.getMessage(), "/usuario/perfil");
            return null; 
        }
    }
}
