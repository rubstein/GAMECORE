package gamecore.com.gamecore.controller;

import java.time.LocalDate;

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
    public String registro(ModelMap m) {
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
                            @RequestParam LocalDate nuevaEdad) throws DangerException {
                                try 
                                {
                                    this.usuarioService.validarUsuarioRegistro(nuevoUsuario, nuevaContrasenya, nuevoEmail, nuevaEdad);
                                }
                                catch (Exception e) 
                                {
                                    PRG.error("Error al registrar el usuario", "/usuario/registro");
                                }
                        
                                return "redirect:/usuario/registro";
}

    @GetMapping("/login")
    public String login(ModelMap m) 
    {
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
}
