document.getElementById("cambiarContrasenya").onclick = function () {
    
    if (!validarContrasenya()) {
        document.getElementById("infoContrasenya").innerHTML =
            "Se permite entre 8 y 20 caracteres sin espacios con al menos una mayúscula, una minúscula, un número y un caracter especial.";
        return;
    }
    if (!validarIgualdad()) {
        document.getElementById("infoContrasenya").innerHTML =
            "Las contraseñas no coinciden. Inténtalo de nuevo.";
        return;
    }

    $.ajax({
        type: "POST",
        url: "/usuario/contrasenya",
        data: {
            contrasenyaActual: document.getElementById("contrasenyaActual").value.trim(),
            nuevaContrasenya: document.getElementById("nuevaContrasenya").value.trim(),
            confirmarContrasenya: document.getElementById("confirmarContrasenya").value.trim()
        },
        success: function (response) {
            if (response.success) {
                document.getElementById("infoContrasenya").innerHTML = "Contraseña cambiada correctamente. Redirigiendo...";
                document.getElementById("infoContrasenya").style.color = "green";
                setTimeout(function () {
                window.location.href = "/usuario/perfil";
                }
                , 2000);
            } else {
                document.getElementById("infoContrasenya").innerHTML = response.message || "Error al cambiar la contraseña.";
            }
        },
        error: function () {
            document.getElementById("infoContrasenya").innerHTML = "Error en la solicitud. Por favor, inténtalo más tarde.";
        }
    });
};

function validarContrasenya() {
    var nuevaContrasenya = document.getElementById("nuevaContrasenya").value.trim();
    var miRegex = /^(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@._])[^\s]{8,20}$/;
    return miRegex.test(nuevaContrasenya);
}

function validarIgualdad() {
    var nuevaContrasenya = document.getElementById("nuevaContrasenya").value.trim();
    var confirmarContrasenya = document.getElementById("confirmarContrasenya").value.trim();
    return nuevaContrasenya === confirmarContrasenya;
}

$('#togglePasswordActual').click(function () {
        var passwordField = $('#contrasenyaActual');
        var type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);

        $(this).text(type === 'password' ? '👁️' : '🙈');
});


$('#togglePasswordNueva').click(function () {
        var passwordField = $('#nuevaContrasenya');
        var type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);

        $(this).text(type === 'password' ? '👁️' : '🙈');
});