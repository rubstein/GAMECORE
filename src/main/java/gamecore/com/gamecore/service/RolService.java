package gamecore.com.gamecore.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Rol;
import gamecore.com.gamecore.repository.RolRepository;

@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;

    public void c(String nombre)  {
        rolRepository.save( new Rol(nombre) );
    }

    public List<Rol> r() {
        return rolRepository.findAll();
    }

    public Rol findRolByName(String nombre) {
        return rolRepository.findRolByNombre(nombre);
    }

    public Rol findById(Long id) throws Exception {
        return rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }
}
