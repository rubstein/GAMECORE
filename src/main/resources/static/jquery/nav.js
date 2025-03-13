$('img:eq(0)').on('click', function(){
    document.location.href = "home.html";
});

$('a').on('mouseover', function(){
    $(this).css({
        'transform': 'scale(1.1)',
        'transition': '0.3s'
    });
}).on('mouseout', function(){
    $(this).css({
        'transform': 'scale(1)',
        'transition': 'transform 0.3s'
    });
});