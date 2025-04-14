package gamecore.com.gamecore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gamecore.com.gamecore.entity.Usuario;
import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(ModelMap m, HttpSession hs){
        Usuario u = (Usuario) hs.getAttribute("usuario");
        m.put("usuario", u);
        m.put("view", "/home/home");
        return "_t/frame";
    }
    @PostMapping("/")
    public String registro(ModelMap m) {
        m.put("view", "/home/home");
        return "_t/frame";
    }
    
}
