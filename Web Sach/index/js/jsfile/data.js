$(function(){
    var $datas = $('#data') 
    $.ajax({    
        type: 'GET',
        url:'http://localhost:8080/products',
        success : function(data)
        {
            console.log('success',data);
        }
    })



}) 