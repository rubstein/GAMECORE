/*document.getElementById("redireccionar").onclick = function (event) {
    if (!document.getElementById("id-terminos").checked) {
        alert("Debes aceptar los términos y las condiciones");
        event.preventDefault();
    } else if (!todoCorrecto()) {
        event.preventDefault();
    } else {
        alert("Registro correcto");
    }
}*/

function validarUsuario() {
    /*
    QUE TENGA AL MENOS 5 CARACTERES Y MAXIMO 15 SIN ESPACIOS
    SE PERMITE CARACTERES ESPECIALES SOLO GUION BAJO PUNTO Y NUMEROS
    */
    var nombreUsuario = document.getElementById("idUsuario").value.trim();
    var miRegex = /^[A-Za-z0-9_.]{5,15}$/;
    if (!miRegex.test(nombreUsuario)) {
        document.getElementById("idUsuario").style.borderColor = "red";
        document.getElementById("infoUsuario").innerHTML = "Nombre de usuario incorrecto. Se permite entre 5 y 15 caracteres sin espacios con puntos, numeros y guión bajo";
        return false;
    } else {
        document.getElementById("idUsuario").style.borderColor = "black";
        document.getElementById("infoUsuario").innerHTML = "";
        return true;
    }
}

function validarContrasenya() {
    /*
    QUE TENGA AL MENOS 8 CARACTERES Y MAXIMO 20 SIN ESPACIOS
    AL MENOS UNA MAYUSCULA, UNA MINUSCULA, UN NUMERO, Y UN CARACTER ESPECIAL
    */
    var contrasenya = document.getElementById("idContrasenya").value.trim();
    var miRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[\W_]).{8,20}$/;
    if (!miRegex.test(contrasenya)) {
        document.getElementById("idContrasenya").style.borderColor = "red";
        document.getElementById("infoContrasenya").innerHTML = "Contraseña incorrecta. Se permite entre 8 y 20 caracteres sin espacios con al menos una mayúscula, una minúscula, un número y un caracter especial";
        return false;
    } else {
        document.getElementById("idContrasenya").style.borderColor = "black";
        document.getElementById("infoContrasenya").innerHTML = "";
        return true;
    }
}

function comprobarContrasenya2() {
    /* COMPARAR QUE AMBAS CONTRASEÑAS SEAN IGUALES */
    var contrasenya = document.getElementById("idContrasenya").value.trim();
    var contrasenya2 = document.getElementById("idContrasenya2").value.trim();
    if (contrasenya !== contrasenya2) {
        document.getElementById("idContrasenya2").style.borderColor = "red";
        document.getElementById("infoContrasenya2").innerHTML = "Las contraseñas no coinciden";
        return false;
    } else {
        document.getElementById("idContrasenya2").style.borderColor = "black";
        document.getElementById("infoContrasenya2").innerHTML = "";
        return true;
    }
}

function validarEmail() {
    /* SOLAMENTE CORREOS VALIDOS DE GOOGLE, HOTMAIL, YAHOO Y OUTLOOK */
    var email = document.getElementById("idEmail").value.trim();
    var miRegex = /^[a-zA-Z0-9._-]+@(gmail|hotmail|yahoo|outlook)\.com$/;
    if (!miRegex.test(email)) {
        document.getElementById("idEmail").style.borderColor = "red";
        document.getElementById("infoEmail").innerHTML = "Email incorrecto. Se permite gmail, hotmail, yahoo y outlook";
        return false;
    } else {
        document.getElementById("idEmail").style.borderColor = "black";
        document.getElementById("infoEmail").innerHTML = "";
        return true;
    }
}

function todoCorrecto() {
    return validarUsuario() && validarContrasenya() && comprobarContrasenya2() && validarEmail();
}