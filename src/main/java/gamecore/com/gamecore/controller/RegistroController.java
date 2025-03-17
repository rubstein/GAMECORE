package gamecore.com.gamecore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import gamecore.com.gamecore.service.RegistroService;


@Controller
public class RegistroController {
    @Autowired
    private RegistroService registroService;
    
    @GetMapping("/registro")
    public String login(ModelMap m) {
        m.put("view", "registro/registro");
        return "_t/frame";
    }
    @GetMapping("/registro/terminos")
    public String terminos(ModelMap m) {
        m.put("view", "registro/terminos");
        return "_t/frame";
    }
    

}
