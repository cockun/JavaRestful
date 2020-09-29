
$( document ).ready(function()
{
    $(function(){
        $.ajax({    
            type: 'GET',
            url:'http://localhost:8080/products',
            success : function(data)
            {
                
                var coc = data;
                for(i=0;i<=coc.data.length;i++)
                {
                    console.log(coc.data[i].name);
                }
            }
        });
    
    
    
    }) ;
})
