// Example of how a component should be initialized via JavaScript
// This script logs the value of the component's text property model message to the console

(function() {
    "use strict";
    if(!document.getElementsByClassName("cmp-progressbar__bar").length > 0)
        {return;}
    var element = document.getElementsByClassName("cmp-progressbar__bar");
    var completed = document.getElementsByClassName("cmp-progressbar__label--completed");
    var remaining = document.getElementsByClassName("cmp-progressbar__label--remaining");    
    var width = parseInt(completed[0]?.innerHTML); 
    var rem = parseInt(remaining[0]?.innerHTML);
    console.log("width + rem"+(width + rem));
    var tot = width + rem == 100 ? width + rem : 100;
    console.log("tot"+tot);
    var identity = setInterval(scene, 10); 
        function scene() { 
            if (width >= tot ) { 
            clearInterval(identity); 
            } else { 
            width++;  
            element[0].style.width = width + '%';  
            element[0].innerHTML = width * 1  + '%'; 
            } 
        }
    
    

}());
