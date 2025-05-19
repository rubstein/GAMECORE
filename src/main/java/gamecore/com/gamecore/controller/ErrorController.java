package gamecore.com.gamecore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    
    @GetMapping("/404")
    public String error404(ModelMap m) {
        m.addAttribute("errorMessage", "Página no encontrada");
        return "error/404"; 
    }

    @GetMapping("/500")
    public String error500(ModelMap m) {
        m.addAttribute("errorMessage", "Error interno del servidor");
        return "error/500"; 
    }
}

