package gamecore.com.gamecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Plataforma;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long>{
    public Plataforma findByNombre(String nombre);

}
