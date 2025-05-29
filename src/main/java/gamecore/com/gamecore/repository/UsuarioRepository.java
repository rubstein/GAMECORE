package gamecore.com.gamecore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamecore.com.gamecore.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByNombreUsuario(String nombreUsuario);
    public boolean existsByNombreUsuario(String nombreUsuario);
    public boolean existsByEmail(String email);
    public Usuario findByEmail(String email);
}
