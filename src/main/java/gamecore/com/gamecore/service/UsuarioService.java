package gamecore.com.gamecore.service;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Rol;
import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.entity.Videojuego;
import gamecore.com.gamecore.repository.PuntuacionRepository;
import gamecore.com.gamecore.repository.RolRepository;
import gamecore.com.gamecore.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PuntuacionRepository puntuacionRepository;

    @Autowired
    private EmailService emailService;

    public Usuario validarUsuarioRegistro(String nombreUsuario, String contrasenya, String email, String rol) {
        Usuario u = new Usuario(nombreUsuario, new BCryptPasswordEncoder().encode(contrasenya), email);
        u.setVerificationToken(UUID.randomUUID().toString());
        u.setVerificado(false);
        u.getRoles().add(rolRepository.findRolByNombre(rol));
        usuarioRepository.save(u);
        emailService.enviarEmailVerificacion(email, nombreUsuario, u.getVerificationToken());

        return usuarioRepository.save(u);
    }

    public Usuario usuarioLogin(String nombreUsuario, String contrasenya) throws Exception {
        Usuario u = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (u == null) {
            throw new Exception("Usuario no encontrado");
        }
        if (!u.isVerificado()) {
            boolean esAdmin = u.getRoles().stream().anyMatch(r -> r.getNombre().equalsIgnoreCase("ADMIN"));
            if (!esAdmin) {
                throw new Exception("Por favor, verifica tu cuenta en tu email antes de iniciar sesión.");
            }
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

    @Transactional
    public void d(Long id) throws Exception {
        puntuacionRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
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

    public boolean comprobarContrasenya(Usuario usuario, String contrasenyaActual) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(contrasenyaActual, usuario.getContrasenya());
    }

    public void cambiarContrasenya(Usuario usuario, String nuevaContrasenya) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setContrasenya(encoder.encode(nuevaContrasenya));
        usuarioRepository.save(usuario);
    }

}
