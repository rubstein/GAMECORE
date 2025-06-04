package gamecore.com.gamecore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailRegistro(String nuevoEmail, String nuevoUsuario) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            helper.setTo(nuevoEmail);
            helper.setFrom("gamecoreenterprise@gmail.com");
            helper.setSubject("BIENVENIDO A GAMECORE");
            helper.setText("Hola " + nuevoUsuario
                    + ", ahora usted pertenece a la comunidad de GAMECORE y podrá compartir su opinión de sus juegos favoritos.",
                    true);

            mailSender.send(mensaje);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo");
        }
    }

    public void enviarEmailVerificacion(String email, String usuario, String token) {
    String enlace = "http://localhost:8080/usuario/verificar?token=" + token;
    try {
        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
        helper.setTo(email);
        helper.setFrom("gamecoreenterprise@gmail.com");
        helper.setSubject("Verifica tu cuenta en GAMECORE");
        helper.setText("Hola " + usuario + ",<br>Haz clic en el siguiente enlace para verificar tu cuenta:<br><a href='" + enlace + "'>Verificar cuenta</a>", true);
        mailSender.send(mensaje);
    } catch (MessagingException e) {
        throw new RuntimeException("Error al enviar correo");
    }
}

}
