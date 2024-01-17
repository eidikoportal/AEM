
$(document).ready(function() {
    console.log("ready");
    // This WILL work because we are listening on the 'document', 
    // for a click on an element with an ID of #test-element
    $(document).on("click","#test-element",function() {
        alert("click bound to document listening for #test-element");
    });

    // This will NOT work because there is no '#test-element' ... yet
    $("#test-element").on("click",function() {
        alert("click bound directly to #test-element");
    });

    // Create the dynamic element '#test-element'
    $('body').append('<div id="test-element">Click mee</div>');
});