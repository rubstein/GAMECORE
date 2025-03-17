$('#submit').on('mouseover', function(){
    $(this).css({
        'transform': 'scale(1.1)',
        'transition': '0.5s'
    })
}).on('mouseout', function(){
    $(this).css({
        'transform': 'scale(1)',
        'transition': '0.5s'
    })
})
$('#idNuevaEdad').datepicker({
    changeMonth: true,
    changeYear: true
});