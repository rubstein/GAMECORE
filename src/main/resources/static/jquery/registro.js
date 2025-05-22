$(document).ready(function () {
    // Mostrar/ocultar contraseña (opcional)
    $('#togglePassword').click(function () {
        var passwordField = $('#idContrasenya');
        var type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        $(this).text(type === 'password' ? '👁️' : '🙈');
    });

    $('#redireccionar').on('click', function (event) {
        event.preventDefault();

        if (!$('#id-terminos').is(':checked')) {
            mostrarMensaje("Debes aceptar los términos y las condiciones", "error");
            return;
        }
        if (!todoCorrecto()) {
            console.log("Validación fallida");

            mostrarMensaje("Corrige los errores antes de continuar", "error");
            return;
        }

        $.ajax({
            url: '/usuario/registro',
            type: 'POST',
            data: {
                nuevoUsuario: $('#idUsuario').val(),
                nuevaContrasenya: $('#idContrasenya').val(),
                nuevaContrasenya2: $('#idContrasenya2').val(),
                nuevoEmail: $('#idEmail').val()
            },
            success: function (response) {
                console.log("Respuesta:", response);
                if (response.success) {
                    mostrarMensaje("Registro correcto. Redirigiendo...", "success");
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
    });

    function mostrarMensaje(mensaje, tipo) {
        $('#error-message').show().text(mensaje).removeClass('success error').addClass(tipo);
    }
});