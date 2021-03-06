/////////////////////
var data = JSON.parse(sessionStorage.getItem("product")).product;
var dataObj = JSON.parse(sessionStorage.getItem("product"));
var placeAdd = document.getElementsByClassName("placeAdd")[0];
var total = document.getElementsByClassName('totalPrice')[0];
total.innerText = formatDollar(dataObj.total*1) + " đ";
var discount = document.getElementsByClassName('discount')[0];
///////////////////////////
//Auto Fill Info
var dataInfo = JSON.parse(sessionStorage.getItem('userInfo'));
document.getElementById('name').value=dataInfo.name;
document.getElementById('phone').value=dataInfo.phone;
document.getElementById('address').value=dataInfo.address;
document.getElementById('email').value=dataInfo.email;


////

if(dataObj.discount <= 100){
  discount.innerText = formatDollar(Number(dataObj.discount)*Number(dataObj.total)/100) +" đ";
}
else{
  discount.innerText = formatDollar(Number(dataObj.discount)) +" vnđ";
}

var lastPrice = document.getElementsByClassName('lastPrice')[0];
lastPrice.innerText = formatDollar(dataObj.lastPrice*1) + " đ";


for (let i = 0 ; i < data.length ; i++){
    var cartRow = document.createElement('div');
    var template = `
        <div class="product">
            <div class="left">
                <div class="bold"><span class="quantity">${data[i].quantity}</span>  x  ${data[i].name}</div>
                <div class="fade">${data[i].idcategory}</div>
            </div>
            <div class="right">
                 ${formatDollar(Number(data[i].price)*Number(data[i].quantity))} đ
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
        a[i]={
            "idProduct":data[i].id,
            "quantity":data[i].quantity
        }
    }
    console.log(a);
    var obj={
        "billOrderReqInfos":a,
        "id": "",
        "nameUser": JSON.parse(sessionStorage.getItem("userInfo")).user,
        "promotionCode": JSON.parse(sessionStorage.getItem("promotionCodeUsed")),
        "phone": phone,
        "email": email,
        "address": address
      };
    $.ajax({
        type:"POST",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/bills",
        data:JSON.stringify(obj),
        success:function(data)
        {
          if (data.success){
            alert("Success");
            sessionStorage.removeItem("product");
            sessionStorage.removeItem("promotionCodeUsed");
            window.location="./index.html";
            console.log(data);
          }
          else{
            alert(data.message);
          }
        },
        error:function(data)
        {
            alert("Có Lỗi !!!");
            
        },
       
    })
    
}

/// checklogin 
function logOut(){
    sessionStorage.removeItem("userInfo");
    return;
  }
  
  window.addEventListener('DOMContentLoaded', (event) => {
    if (sessionStorage.getItem("userInfo") !== null) {
      document.getElementById('button').innerHTML=`
        <div id="button">
          <i class="far fa-handshake" ></i><li style="font-size:15px; margin: 0 10px; font-weight:bold">Xin chào ${JSON.parse(sessionStorage.getItem("userInfo")).name}</li> 
          <i class="fas fa-sign-out-alt" id="logoutButton"></i><li><a onclick="logOut()" href="./index.html">Đăng Xuất</a></li>
        </div>  
      `
    }
    else{
    document.getElementById('button').innerHTML = `
    <div id="button">
    <i class="fas fa-user" id="loginButton"></i><li><a href="login.html">Đăng Nhập</a></li> 
    <i class="fas fa-pen" id="registerButton"></i><li><a href="register.html">Đăng Ký</a></li>
    
    </div>  
    `
    }
  });



////