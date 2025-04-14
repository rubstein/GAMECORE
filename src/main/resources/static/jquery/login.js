$('#submit').on('mouseover', function(){
    $(this).css({
        'transform': 'scale(1.05)',
        'transition': '0.5s'
    })
}).on('mouseout', function(){
    $(this).css({
        'transform': 'scale(1)',
        'transition': '0.5s'
    })
})