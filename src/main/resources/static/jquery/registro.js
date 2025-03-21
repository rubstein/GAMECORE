$('#redireccionar').on('mouseover', function(){
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
$('#idFecha').datepicker({
    dateFormat: 'dd-mm-yy',
    changeMonth: true,
    changeYear: true
});