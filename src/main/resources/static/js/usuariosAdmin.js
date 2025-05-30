document.querySelectorAll('.borrar-usuario').forEach(function(btn) {
    btn.addEventListener('click', function(e) {
        if (!confirm("¿Estás seguro de que quieres eliminar este usuario? Esta acción no se puede deshacer.")) {
            e.preventDefault();
        }
    });
});