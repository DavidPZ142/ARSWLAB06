var apiclient = (function (){

    return {
        getBlueprintsByAuthor: function (author, callback){
            $.get("/blueprints/"+author, function (data){
                callback(null, data);
            }).fail(function (){
                alert("Error");
            });
        },

        getBlueprintsByNameAndAuthor: function (author, name, callback){
            $.get("/blueprints/"+author+"/"+name,function (data){
                callback(null,data)
            }).fail(function (){
                alert("Error");
            });
        }
    }


})();