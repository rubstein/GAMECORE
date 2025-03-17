document.getElementsByTagName("i")[0].onclick = mostrarContrasenya

function validarUsuario()
{
    var usuario = document.miFormulario.usuario.value;
    var regex = /^[A-Za-z0-9]+$/;

    if (regex.test(usuario) && usuario.length !== 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}

function validarContrasenya(){
}

function validarFormulario(){
}

function mostrarContrasenya(){
    if (document.miFormulario.contrasenya.type === "password"){
        document.miFormulario.contrasenya.type = "text";
    } else {
        document.miFormulario.contrasenya.type = "password";
    }
}
