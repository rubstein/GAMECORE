package gamecore.com.gamecore.service;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Rol;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.repository.RolRepository;
import gamecore.com.gamecore.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public Usuario validarUsuarioRegistro(String nombreUsuario, String contrasenya, String email, String rol) {
        Usuario u = new Usuario(nombreUsuario, new BCryptPasswordEncoder().encode(contrasenya), email);

        u.getRoles().add(rolRepository.findRolByNombre(rol));

        return usuarioRepository.save(u);
    }

    public Usuario usuarioLogin(String nombreUsuario, String contrasenya) throws Exception {
        Usuario u = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (u == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (!new BCryptPasswordEncoder().matches(contrasenya, u.getContrasenya())) {
            throw new Exception("Contraseña incorrecta");
        }
        return u;
    }

    public Usuario u(Long id, String nuevoNombre, String nuevoEmail) {
        Usuario u = usuarioRepository.findById(id).orElse(null);
        if (u != null) {
            u.setNombreUsuario(nuevoNombre);
            u.setEmail(nuevoEmail);
            return usuarioRepository.save(u);
        }
        return null;
    }

    public void actualizarRolesUsuario(Long usuarioId, List<Long> nuevosRolesIds) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Collection<Rol> nuevosRoles = rolRepository.findAllById(nuevosRolesIds);

        usuario.setRoles(nuevosRoles);

        usuarioRepository.save(usuario);
    }

    public List<Usuario> r() {
        return usuarioRepository.findAll();
    }

    public void d(Long id) throws Exception {
        usuarioRepository.deleteById(id);
    }

    public boolean existsByName(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String nombreEmail) {
        return usuarioRepository.existsByEmail(nombreEmail);
    }

    public Usuario findById(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public Usuario findByNombreUsuario(String nombreUsuario) throws Exception {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Usuario findByEmail(String email) {
    return usuarioRepository.findByEmail(email);
    }

    public Usuario save(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public void agregarAFavoritos(Usuario usuario, Videojuego juego) {
        if (usuario.getFavoritos() == null) {
            usuario.setFavoritos(new ArrayList<>());
        }

        if (!usuario.getFavoritos().contains(juego)) {
            usuario.getFavoritos().add(juego);
            usuarioRepository.save(usuario);
        }
    }

    @Transactional
    public Usuario findByIdConFavoritos(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        usuario.getFavoritos().size();

        return usuario;
    }

    public void eliminarDeFavoritos(Usuario usuario, Videojuego juego) {
        usuario.getFavoritos().remove(juego);
        usuarioRepository.save(usuario);
    }
}
