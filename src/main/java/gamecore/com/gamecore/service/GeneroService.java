package gamecore.com.gamecore.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Genero;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.repository.GeneroRepository;

@Service
public class GeneroService {
    
    @Autowired
    private GeneroRepository generoRepository;

    public Genero buscarPorNombre(String nombre) {
        return generoRepository.findByNombre(nombre); 
    }

    public Collection<Genero> r() {
        return generoRepository.findAll();
    }

    public void c(String nombre) throws DangerException {
        generoRepository.save(new Genero(nombre));
    }
}
