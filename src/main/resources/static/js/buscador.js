$(document).ready(function () {
    $("#id-busqueda").on("input", function () 
    {
        var query = $(this).val().trim();

        if (query.length = 1)
        {
            $.ajax({
                url: "/videojuego/buscar",
                method: "GET",
                data: { q: query },

                success: function (data) {
                    let html = "";

                    if (data.length > 0) 
                    {
                        data.forEach(function (juego) 
                        {
                            html += `<a href="/videojuego/${juego.slug}">${juego.nombre}</a>`;
                        });
                    }
                    else
                    {
                        html = "<div style='padding:10px;'>No se encontraron juegos</div>";
                    }
                    $("#resultados-busqueda").html(html).show();
                },

                error: function () {
                    $("#resultados-busqueda").html("<div style='padding:10px;'>No se encontraron juegos</div>").show();
                }
            });
        } else {
            $("#resultados-busqueda").empty().hide();
        }
    });

    // Ocultar sugerencias si se hace clic fuera del input o la lista
    $(document).on("click", function (e) {
        if (!$(e.target).closest(".busqueda").length) {
            $("#resultados-busqueda").hide();
        }
    });
});
