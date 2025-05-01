package gamecore.com.gamecore.controller;

import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.GeneroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/genero")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;

    @GetMapping("r")
    public String r(
            ModelMap m) {
        m.put("generos", generoService.r());
        m.put("view", "genero/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(
            ModelMap m) {
        m.put("view", "genero/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
            @RequestParam("nombre") String nombre) throws DangerException {
        try {
            this.generoService.c(nombre);
        } catch (Exception e) {
            PRG.error("El genero " + nombre + " ya está registrado", "/genero/c");
        }
        return "redirect:/genero/r";
    }
}
