$(document).ready(function () {
    $('#idFecha').datepicker({
        dateFormat: 'dd-mm-yy',
        changeMonth: true,
        changeYear: true
    });

    $('#togglePassword').click(function () {
        var passwordField = $('#idContrasenya');
        var type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);

        $(this).text(type === 'password' ? '👁️' : '🙈');
    });
});