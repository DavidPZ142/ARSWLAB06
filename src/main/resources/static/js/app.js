
var module = apimock;

var app=(function(){

    function getBluePrintname(){
        author = $(#author).val();
        if (author == ""){
            alert("Ingrese un nombre");

        }
        else{
            module.getBlueprintsByAuthor(author,(req,res) =>{
                alert(author); });
        }
    }

    return{
        getBluePrintname : getBluePrintname
    }

})();