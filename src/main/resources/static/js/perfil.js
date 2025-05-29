function activarEdicion() {
    var valorOriginal = document.getElementById("editar").value;
    var idOriginal = document.getElementById("editar").id;

    var botonGuardar = document.createElement("input");
    botonGuardar.type = "button";
    botonGuardar.value = "Guardar Cambios";
    botonGuardar.id = "guardarCambios";
    botonGuardar.className = "button-perfil";

    document.getElementById("nuevoNombreUsuario").disabled = false;
    document.getElementById("nuevoEmail").disabled = false;

    document.getElementById("editar").value = "Cancelar";
    document.getElementById("editar").id = "cancelar";

    if (!document.getElementById("guardarCambios")) {
        document.getElementById("formulario").appendChild(botonGuardar);
    }

    document.getElementById("cancelar").onclick = function () {
        document.getElementById("nuevoNombreUsuario").disabled = true;
        document.getElementById("nuevoEmail").disabled = true;

        if (document.getElementById("guardarCambios")) {
            document.getElementById("formulario").removeChild(document.getElementById("guardarCambios"));
        }

        document.getElementById("cancelar").value = valorOriginal;
        document.getElementById("cancelar").id = idOriginal;
        document.getElementById("editar").onclick = activarEdicion;
    };


    botonGuardar.onclick = function () {

        document.getElementById("nuevoNombreUsuario").disabled = true;
        document.getElementById("nuevoEmail").disabled = true;

        if (document.getElementById("guardarCambios")) {
            document.getElementById("formulario").removeChild(document.getElementById("guardarCambios"));
        }

        document.getElementById("cancelar").value = valorOriginal;
        document.getElementById("cancelar").id = idOriginal;
        document.getElementById("editar").onclick = activarEdicion;

        var nuevoNombreUsuario = document.getElementById("nuevoNombreUsuario").value;
        var nuevoEmail = document.getElementById("nuevoEmail").value;

        $.ajax({
            url: '/usuario/perfil',
            type: 'POST',
            data: {
                nuevoNombreUsuario: nuevoNombreUsuario,
                nuevoEmail: nuevoEmail
            },
            success: function (response) {
                if (response && response.success) {
                    mostrarMensajePerfil('Datos guardados correctamente', 'ok');
                } else if (response && response.message) {
                    mostrarMensajePerfil(response.message, 'error');
                } else {
                    mostrarMensajePerfil('No se ha podido guardar.', 'error');
                }
                document.getElementById("nuevoNombreUsuario").disabled = true;
                document.getElementById("nuevoEmail").disabled = true;
                if (document.getElementById("guardarCambios")) {
                    document.getElementById("formulario").removeChild(document.getElementById("guardarCambios"));
                }
                document.getElementById("cancelar").value = valorOriginal;
                document.getElementById("cancelar").id = idOriginal;
                document.getElementById("editar").onclick = activarEdicion;
            },
            error: function () {
                mostrarMensajePerfil('No se ha podido guardar por un error de servidor.', 'error');
            }
        });
    };
}

document.getElementById("editar").onclick = function () {
    activarEdicion();
};

function mostrarMensajePerfil(msg, tipo) {
    var div = document.getElementById("mensajeUsuario");
    div.style.display = "block";
    div.innerText = msg;
    div.className = tipo === "ok" ? "mensaje-ok" : "mensaje-error";
}