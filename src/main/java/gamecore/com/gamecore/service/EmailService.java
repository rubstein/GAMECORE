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
                    + ", ahora usted pertenece a la comunidad de GAMECORE y podrá compartir su opinión de sus juegos favoritos",
                    true);

            mailSender.send(mensaje);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo");
        }
    }

}
