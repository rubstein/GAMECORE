package gamecore.com.gamecore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.helper.PRG;
import gamecore.com.gamecore.service.PlataformaService;

@Controller
@RequestMapping("/plataforma")
public class PlataformaController {
    
    @Autowired
    private PlataformaService plataformaService;

    @GetMapping("r")
    public String r(
            ModelMap m) {
        m.put("plataformas", plataformaService.r());
        m.put("view", "plataforma/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(
            ModelMap m) {
        m.put("view", "plataforma/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
            @RequestParam("nombre") String nombre) throws DangerException {
        try {
            this.plataformaService.c(nombre);
        } catch (Exception e) {
            PRG.error("La plataforma " + nombre + " ya está registrada", "/platafroma/c");
        }
        return "redirect:/plataforma/r";
    }
}
