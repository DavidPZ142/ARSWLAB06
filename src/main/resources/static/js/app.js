
var module = apimock;

var app=(function(){
    var _author = null;
    var _plano = null;
    let puntos = 0;

    function getBluePrintname(author){
        _author = author;
        if (_author == ""){
            alert("Escriba un nombre :v");
        }else{
           module.getBlueprintsByAuthor(_author,mapFunction);
        }
    };

    var mapFunction = function(variable){
        if(variable != null){
            //alert("Entra a algo");
            let html = "<tr>";
            var arreglo = variable.map(function (blueprint){
                html+= "<td>" + blueprint.name
                html+= "<td>" + blueprint.points.length
                html+= "</tr>"
                puntos += blueprint.points.length;
                //console.log(html);  
                $('#tbody').html(html)    
            })
            $('#totalUserPoint').html(puntos)

         
            /*
            let total = variable.reduce(function(sum,num){
            
                return sum.points.length + num.points.length

                })
            console.log(total)
            $('#totalUserPoint').html(total)
            */

                
                
        }else{
            alert("No encontro al autor");
        }

    
    }


    

    return{
        getBluePrintname : getBluePrintname
    };

})();