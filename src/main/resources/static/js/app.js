
var module = apimock;

var app=(function(){

    function getBluePrintname(author){
        if (author == ""){
            alert("Escriba un nombre :v");
        }else{
           module.getBlueprintsByAuthor(author);
           
        }
    };

    return{
        getBluePrintname : getBluePrintname
    };

})();