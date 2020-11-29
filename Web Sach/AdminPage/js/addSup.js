var btnAccept = document.getElementsByClassName('btnAccept')[0];
btnAccept.addEventListener('click',addSup)



function addSup()
{
    
  
    let obj={
        "id": '',
        "name": document.getElementById("name").value,
        "address":document.getElementById("address").value,
        "phone": document.getElementById("phone").value,

    }
    $.ajax({
        type:"POST",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/admin/supplier",
        data:JSON.stringify(obj),
        success:function(data)
        {
            alert(data.message);
            console.log(data);
            location.reload()
        },

    })
    
}