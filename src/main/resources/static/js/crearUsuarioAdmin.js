function validarUsuario() {
    /*
    QUE TENGA AL MENOS 5 CARACTERES Y MAXIMO 15 SIN ESPACIOS
    SE PERMITE CARACTERES ESPECIALES SOLO GUION BAJO PUNTO Y NUMEROS
    */
    var inputNombreUsuario = document.getElementById("nombreUsuario");
    var nombreUsuario = inputNombreUsuario.value.trim();
    var miRegex = /^[A-Za-z0-9_.]{5,15}$/;
    if (!miRegex.test(nombreUsuario)) {
        inputNombreUsuario.style.borderColor = "red";
        document.getElementById("infoUsuario").innerHTML = "Nombre de usuario incorrecto. Se permite entre 5 y 15 caracteres sin espacios con puntos, numeros y guión bajo";
        return false;
    } else {
        inputNombreUsuario.style.borderColor = "black";
        document.getElementById("infoUsuario").innerHTML = "";
        return true;
    }
}

function validarEmail() {
    /* SOLAMENTE CORREOS VALIDOS DE GOOGLE, HOTMAIL, YAHOO Y OUTLOOK */
    var inputEmail = document.getElementById("email");
    var email = inputEmail.value.trim();
    var miRegex = /^[a-zA-Z0-9._-]+@(gmail|hotmail|yahoo|outlook)\.com$/;
    if (!miRegex.test(email)) {
        inputEmail.style.borderColor = "red";
        document.getElementById("infoUsuario").innerHTML = "Email incorrecto. Se permite gmail, hotmail, yahoo y outlook";
        return false;
    } else {
        inputEmail.style.borderColor = "black";
        document.getElementById("infoUsuario").innerHTML = "";
        return true;
    }
}

function validarContrasenya() {
    /*
    QUE TENGA AL MENOS 8 CARACTERES Y MAXIMO 20 SIN ESPACIOS
    AL MENOS UNA MAYUSCULA, UNA MINUSCULA, UN NUMERO, Y UN CARACTER ESPECIAL
    */
    var inputContrasenya = document.getElementById("contrasenya");
    var contrasenya = inputContrasenya.value.trim();
    var miRegex = /^(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@._])[^\s]{8,20}$/;
    if (!miRegex.test(contrasenya)) {
        inputContrasenya.style.borderColor = "red";
        document.getElementById("infoUsuario").innerHTML = "Contraseña incorrecta. Se permite entre 8 y 20 caracteres sin espacios con al menos una mayúscula, una minúscula, un número y un caracter especial";
        return false;
    } else {
        inputContrasenya.style.borderColor = "black";
        document.getElementById("infoUsuario").innerHTML = "";
        return true;
    }
}

function todoCorrecto() {
    return validarUsuario() && validarContrasenya() && validarEmail();
}