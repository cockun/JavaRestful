var data = JSON.parse(sessionStorage.getItem("product")).product;
var dataObj = JSON.parse(sessionStorage.getItem("product"));
var placeAdd = document.getElementsByClassName("placeAdd")[0];
var total = document.getElementsByClassName('totalPrice')[0];
total.innerText = dataObj.total;
var discount = document.getElementsByClassName('discount')[0];
discount.innerText = dataObj.discount;
var lastPrice = document.getElementsByClassName('lastPrice')[0];
lastPrice.innerText = dataObj.lastPrice;


for (let i = 0 ; i < data.length ; i++){
    var cartRow = document.createElement('div');
    var template = `
        <div class="product">
            <div class="left">
                <div class="bold"><span class="quantity">${data[i].quantity}</span>  x  ${data[i].name}</div>
                <div class="fade">Khi đôi môi em còn đỏ mọng</div>
            </div>
            <div class="right">
                 ${Number(data[i].price)*Number(data[i].quantity)}
            </div>
        </div>
    `
    cartRow.innerHTML = template;
    cartRow.classList.add('productCont');
    placeAdd.appendChild(cartRow);
}
function addBill()
{
    var name = document.getElementById("name").value;
    var address=document.getElementById("address").value;
    var phone =document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var a =[];
    for (let i = 0 ; i < data.length ; i++){
        a[i]=data[i];
    }
    console.log(a);
    var obj={
        "total":dataObj.total,
        "date":"asdasd",
        "nameUser":name,
        "code":"BL123123",
        "billInfoModel":a,
        "discount":0,
        "codePromotion":null,
        "pay":false
    }
    $.ajax({
        type:"POST",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/bills",
        data:JSON.stringify(obj),
        success:function(data)
        {
            alert("khon");
        },
        error : alert("ngu")
    })
    
}
