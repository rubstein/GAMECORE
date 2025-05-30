$(document).ready(function () {
    document.getElementById("anyadirUsuario").onclick = function () {

        if ($('input[name="rol"]:checked').length === 0) {
            mostrarMensaje("Debes seleccionar un rol de usuario", "error");
            return;
        }
        if (!todoCorrecto()) {
            console.log("Validación fallida");
            mostrarMensaje("Corrige los errores antes de continuar", "error");
            return;
        }

        $.ajax({
            url: '/admin/usuarios-c',
            type: 'POST',
            data: {
                nombreUsuario: $('#nombreUsuario').val(),
                email: $('#email').val(),
                contrasenya: $('#contrasenya').val(),
                rol: $('input[name="rol"]:checked').val(),
            },
            success: function (response) {
                console.log("Respuesta:", response);
                if (response.success) {
                    mostrarMensaje("Se ha añadido el usuario. Redirigiendo...", "success");
                    setTimeout(function () {
                        window.location.href = '/';
                    }, 2000);
                } else {
                    mostrarMensaje(response.message, "error");
                }
            },
            error: function () {
                mostrarMensaje("Error de servidor. Inténtalo de nuevo.", "error");
            }
        });

        function mostrarMensaje(mensaje, tipo) {
            $('#error-message').show().text(mensaje).removeClass('success error').addClass(tipo);
        }
    }
});