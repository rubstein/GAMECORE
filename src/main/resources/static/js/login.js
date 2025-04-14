document.getElementById('togglePassword').addEventListener('click', function () {
    var input = document.getElementById('idContrasenya');
    var icon = this;
    var isPassword = input.type === 'password';

    input.type = isPassword ? 'text' : 'password';
    icon.classList.toggle('fa-eye');
    icon.classList.toggle('fa-eye-slash');
});