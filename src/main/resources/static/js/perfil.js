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

    document.getElementById("editar").value = "Cancelar"
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
    }
}

document.getElementById("editar").onclick = function () {
    activarEdicion();
}