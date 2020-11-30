var btnAccept = document.getElementsByClassName('btnAccept')[0];
btnAccept.addEventListener('click',addCategory);

function addCategory()
{
    let cate = document.getElementById("category").value;
    let obj = {
        name:cate
    }
    $.ajax({
        type:"POST",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/admin/category",
        data:JSON.stringify(obj),
        success:function(data)
        {
            alert(data.message);
            console.log(data);
        },

    })

}