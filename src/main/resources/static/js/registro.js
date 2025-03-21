var micb = document.getElementById("id-terminos");

document.getElementById("submit").onclick = function(event){
    if (!micb.checked){
        alert("Debes aceptar los términos y las condiciones");
        event.preventDefault()
    }
}