package gamecore.com.gamecore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    public Genero findByNombre(String nombre);
}
