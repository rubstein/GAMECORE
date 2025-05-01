package gamecore.com.gamecore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {

   public Rol findRolByNombre(String nombre);
}