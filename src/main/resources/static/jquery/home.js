$('a').on('mouseover', function(){
    $(this).css({
        'transform': 'scale(1.1)',
        'transition': '0.5s'
    })
}).on('mouseout', function(){
    $(this).css({
        'transform': 'scale(1)',
        'transition': '0.5s'
    })
});

$('.negro').on('mouseover', function(){
    $(this).css('color', 'black');
});
$('.verde').on('mouseover', function(){
    $(this).css('color', 'green');
}).on('mouseout', function(){
    $(this).css('color', 'black');
});
$('.rojo').on('mouseover', function(){
    $(this).css('color', 'red');
}).on('mouseout', function(){
    $(this).css('color', 'black');
});


