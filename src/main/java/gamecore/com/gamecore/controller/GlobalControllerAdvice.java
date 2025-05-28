package gamecore.com.gamecore.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import gamecore.com.gamecore.entity.Genero;
import gamecore.com.gamecore.service.GeneroService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private GeneroService generoService;

    @ModelAttribute("generos")
    public Collection<Genero> generos() {
        return generoService.r();
    }
}