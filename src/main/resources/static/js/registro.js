document.getElementById("redireccionar").onclick = function(event) {
    if (!document.getElementById("id-terminos").checked) {
        alert("Debes aceptar los términos y las condiciones");
        event.preventDefault();
    } else if (!todoCorrecto()) {
        event.preventDefault();
    } else {
        alert("Registro correcto");
    }
}

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
    var miRegex = /^(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@._])[^\s]{8,20}$/;
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

function validarFecha() {
    var fecha = document.getElementById("idFecha").value.trim();
    var miRegex = /^(([0-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(\d{4}))$/;

    if (!miRegex.test(fecha)) {
        document.getElementById("idFecha").style.borderColor = "red";
        document.getElementById("infoFecha").innerHTML = "Formato de fecha incorrecto. Use dd-MM-yyyy.";
        return false;
    }

    var partesFecha = fecha.split("-");
    var dia = parseInt(partesFecha[0], 10);
    var mes = parseInt(partesFecha[1], 10) - 1; 
    var anio = parseInt(partesFecha[2], 10);

    var fechaNacimiento = new Date(anio, mes, dia);
    var fechaActual = new Date();

    var edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();
    if (fechaActual.getMonth() < fechaNacimiento.getMonth() || (fechaActual.getMonth() === fechaNacimiento.getMonth() && fechaActual.getDate() < fechaNacimiento.getDate())) {
        edad--;
    }
    if (edad < 12) {
        document.getElementById("idFecha").style.borderColor = "red";
        document.getElementById("infoFecha").innerHTML = "Debes tener al menos 12 años.";
        return false;
    }
    document.getElementById("idFecha").style.borderColor = "black";
    document.getElementById("infoFecha").innerHTML = "";
    return true;
}
function todoCorrecto() {
    if (validarUsuario() && validarContrasenya() && comprobarContrasenya2() && validarEmail() && validarFecha()) {
        return true;
    } else {
        return false;
    }
}

document.getElementById('togglePassword').addEventListener('click', function () {
    var input = document.getElementById('idContrasenya');
    var icon = this;
    var isPassword = input.type === 'password';

    input.type = isPassword ? 'text' : 'password';
    icon.classList.toggle('fa-eye');
    icon.classList.toggle('fa-eye-slash');
});