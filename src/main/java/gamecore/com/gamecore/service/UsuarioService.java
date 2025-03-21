package gamecore.com.gamecore.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gamecore.com.gamecore.entity.Usuario;
import gamecore.com.gamecore.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarUsuarioRegistro(String nombreUsuario, String contrasenya, String email, LocalDate fecha) {
        Usuario u = new Usuario(nombreUsuario, new BCryptPasswordEncoder().encode(contrasenya), email, fecha);
        
        usuarioRepository.save(u);
    }

    public Usuario validarUsuarioLogin(String nombreUsuario, String contrasenya) throws Exception {
        Usuario u = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (u == null){
            throw new Exception("Usuario no encontrado");
        }

        if (!new BCryptPasswordEncoder().matches(contrasenya, u.getContrasenya())){
            throw new Exception("Contraseña incorrecta");
        }
        return u;
    }
}
