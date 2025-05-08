package gamecore.com.gamecore.init;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.service.GeneroService;
import gamecore.com.gamecore.service.PlataformaService;
import gamecore.com.gamecore.service.RolService;
import gamecore.com.gamecore.service.UsuarioService;
import gamecore.com.gamecore.service.VideojuegoService;
import jakarta.annotation.PostConstruct;

@Component
public class BDinit {

    @Autowired
    private PlataformaService plataformaService;

    @Autowired
    private VideojuegoService videojuegoService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;

    @PostConstruct
    public void init() throws DangerException {
        if (rolService.r().size() == 0) {
            crearGeneros();
            crearPlatafromas();
            crearRoles();
            crearUsuarios();
        }

        if (videojuegoService.r().isEmpty()) {
            crearVideojuego();
        }
    }

    private void crearGeneros() throws DangerException {
        generoService.c("Acción");
        generoService.c("Aventura");
        generoService.c("Rpg");
        generoService.c("Deporte");
        generoService.c("Simulación");
        generoService.c("Estrategia");
        generoService.c("Terror");
        generoService.c("Shooter");
    }

    private void crearPlatafromas() throws DangerException {
        plataformaService.c("PlayStation");
        plataformaService.c("Nintendo");
        plataformaService.c("Xbox");
        plataformaService.c("PC");
    }

    private void crearRoles() {
        rolService.c("admin");
        rolService.c("user");
    }

    private void crearUsuarios() {
        usuarioService.validarUsuarioRegistro("Administrador", "Admin123456#", "admin1@gmail.com", "admin");
    }

    private void crearVideojuego() {

        List<Long> generosRPG = List.of(generoService.buscarPorNombre("Rpg").getId());
        List<Long> plataformasPC = List.of(plataformaService.buscarPorNombre("PC").getId(), plataformaService.buscarPorNombre("PlayStation").getId());

        try {
            videojuegoService.c("Cyberpunk",
                    "Cyberpunk 2077 is an action role-playing game played from a first-person perspective as V, a mercenary whose voice, face, hairstyle, body type and modifications, background, and clothing are customisable.",
                    "/img/Cyber.jpg", LocalDate.of(2020, 12, 10),
                    9.6, "CD PROJEKT RED, WB Games", 87.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Dark Souls III",
                    "Ambientado en el mundo decadente de Lothric, el jugador asume el papel de un no muerto sin llama, cuya misión es devolver a sus tronos a los Señores de la Ceniza, poderosas figuras que una vez vincularon la Primera Llama, una fuente de poder vital para el mundo. A través de escenarios sombríos y ruinas olvidadas, el jugador se enfrenta a criaturas grotescas, jefes colosales y trampas letales.",
                    "/img/DarkSouls3.jpg", LocalDate.of(2016, 4, 12),
                    6.9, "FROM SOFTWARE, BANDAI", 69.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Final Fantasy VII",
                    "Final Fantasy VII es un videojuego de rol japonés que combina una historia épica con exploración, combates por turnos y un mundo de ciencia ficción con toques steampunk. El jugador encarna a Cloud Strife, un exsoldado que se une al grupo rebelde AVALANCHA para luchar contra la corporación Shinra, que está agotando los recursos del planeta.",
                    "/img/FF7.jpg", LocalDate.of(1997, 1, 31),
                    8.3, "Square Enix", 87.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Sekiro",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/sekiro.jpg", LocalDate.of(2019, 3, 22),
                    5.5, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Monster Hunter Wild",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/MHW.jpg", LocalDate.of(2019, 3, 22),
                    5.5, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Red Dead Redemption II",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/RDDII.jpg", LocalDate.of(2019, 3, 22),
                    5.5, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Ghost of Yotey",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/GOY.jpg", LocalDate.of(2019, 3, 22),
                    5.5, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Resident Evil 2",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/residentevil.jpg", LocalDate.of(2019, 3, 22),
                    8.7, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("Rainbow 6",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/R6.jpg", LocalDate.of(2019, 3, 22),
                    5.5, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }

        try {
            videojuegoService.c("God of War Ragnarok",
                    "Sekiro: Shadows Die Twice es un videojuego de acción y aventura con elementos de sigilo y combate intenso, desarrollado por FromSoftware. Ambientado en un Japón feudal ficticio con toques sobrenaturales, el juego pone al jugador en la piel de Sekiro, un shinobi que busca vengar el secuestro de su joven señor y recuperar su honor.",
                    "/img/GOF.jpg", LocalDate.of(2019, 3, 22),
                    9.2, "FromSoftware", 29.9, generosRPG, plataformasPC);
        } catch (DangerException e) {
            e.printStackTrace();
        }
    }
}
