var btnAccept = document.getElementsByClassName('btnAccept')[0];
btnAccept.addEventListener('click',addProduct)



function addProduct()
{
    let detail =document.getElementById("detail").value;
    let discount=document.getElementById("discount").value;
    let idcategory=document.getElementById("idcategory").value;
    let name = document.getElementById("name").value;
    let pic =document.getElementById("img").value;
    let price = document.getElementById("price").value;
    let rootPrice = document.getElementById("code").value;
    let writer = document.getElementById("writer").value;

    let obj={
        "code": "",
        "detail": detail,
        "discount": discount*1,
        "id": "",
        "idcategory": idcategory,
        "name": name,
        "pic": pic,
        "price": price*1,
        "rootPrice": rootPrice*1,
        "writer": writer
    }
    $.ajax({
        type:"POST",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/admin/product",
        data:JSON.stringify(obj),
        success:function(data)
        {
            alert("Success");
            console.log(data);
            location.reload()
        },

    })
    
}

