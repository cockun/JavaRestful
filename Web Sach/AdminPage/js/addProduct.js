var btnAccept = document.getElementsByClassName('btnAccept')[0];
btnAccept.addEventListener('click',addProduct)

$(document).ready(function () {
    callApi("GET","categories").data.forEach(category => {
        $("#categoryCode").append(
          `<option value =${category.name}>${category.name}</option>`
        )
      })
      callApi("GET","admin/supplier").data.forEach(supplier => {
        $("#supplierCode").append(
          `<option value =${supplier.id}>${supplier.name}</option>`
        )
      })
})

function addProduct()
{
    
    let detail =document.getElementById("detail").value;
    let discount=document.getElementById("discount").value;
    let idcategory=document.getElementById("categoryCode").value;
    let name = document.getElementById("name").value;
    let pic =document.getElementById("img").value;
    let price = document.getElementById("price").value;
    let rootPrice = document.getElementById("code").value;
    let writer = document.getElementById("writer").value;
    let supplier = document.getElementById("supplierCode").value;

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
        "writer": writer,
        "idSupplier":supplier
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



function callApi(method, endpoint = "", data = null) {
    var datar;
    $.ajax({
      async: false,
      type: method,
      data: data,
      headers: { "Content-Type": "application/json" },
      url: "http://localhost:8080/" + endpoint,
      success: function (data) {
        datar = data;
      },
    });
    return datar;
  }

