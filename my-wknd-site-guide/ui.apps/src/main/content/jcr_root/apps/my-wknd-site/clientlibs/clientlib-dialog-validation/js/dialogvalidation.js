
console.log('clientlib-dialog-validation loaded');

var registry = $(window).adaptTo("foundation-registry");

registry.register("foundation.validation.validator",{
    selector:"[data-validation=title-validation]",
    validate:function(element){
        let pat = /AEM/;
        let val = $(element).val();
        if(!pat.test(val)){
            return "title should contain AEM string";
        }
    }
});