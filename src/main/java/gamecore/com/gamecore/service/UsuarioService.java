package gamecore.com.gamecore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.repository.RolRepository;
import gamecore.com.gamecore.repository.UsuarioRepository;

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
}
