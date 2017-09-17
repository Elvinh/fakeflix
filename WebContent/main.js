/**
 * 
 */

$(document).ready(function() {
    
    $('div#arrowR').click(function(){
        event.preventDefault();
        $('#list').animate({
          marginLeft: "-=200px"
        }, "fast");
     });
    
    $('div#arrowL').click(function(){
        event.preventDefault();
        $('#list').animate({
          marginLeft: "+=200px"
        }, "fast");
     });
    
});