package gamecore.com.gamecore.service;

import java.util.Collection;

import gamecore.com.gamecore.entity.Plataforma;
import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlataformaService {

    @Autowired
    private PlataformaRepository plataformaRepository;

    public Plataforma buscarPorNombre(String nombre) {
        return plataformaRepository.findByNombre(nombre);
    }

    public Collection<Plataforma> r() {
        return plataformaRepository.findAll();
    }

    public void c(String nombre)
            throws DangerException {
        plataformaRepository.save(new Plataforma(nombre));
    }

    public Plataforma findById(Long id) {
        return plataformaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrado con ID: " + id));
    }
}
