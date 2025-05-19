$(document).ready(function () {
    $('#loginButton').click(function () {
        var usuario = $('#idUsuario').val();
        var contrasenya = $('#idContrasenya').val();

        $.ajax({
            url: '/usuario/login',
            type: 'POST',
            data: { usuario: usuario, contrasenya: contrasenya },
            success: function (response) {
                if (response.success) {
                    window.location.href = '/';
                } else {
                    $('#error-message').show();
                    $('#error-message p').text(response.message);
                }
            },
            error: function () {
                $('#error-message').show();
                $('#error-message p').text('Ocurrió un error inesperado. Inténtalo de nuevo.');
            }
        });
    });

    $('#togglePassword').click(function () {
        var passwordField = $('#idContrasenya');
        var type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);

        $(this).text(type === 'password' ? '👁️' : '🙈');
    });

});