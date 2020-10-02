var queryDict = {}
location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]});
var placeAdd = document.getElementsByClassName("placeAdd")[0];

var dataPost ={
    "field": "name",
    "value": queryDict.value
}

$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/search/products",
      data: dataPost,
      dataType: "json",
      headers: { 
        'Content-Type': 'application/json' 
      },
      crossDomain: true,
      success: function (datas) {
        datas.data.forEach(data => {
            console.log(data);
            $("#placeAdd").append(
                `<div class="productD">
                <a href="single.html?id=${data.id}">
                <div class="inner_content clearfix">
                <div class="product_image">
                    <img src="${data.pic}" alt=""/>
                </div>
                
                <div class="price">
                   <div class="cart-left">  
                        <p class="title">${data.name}</p>
                        <div class="price1">
                          <span class="actual">${data.discount}</span>
                        </div>
                    </div>
                    <div class="cart-right"> </div>
                    <div class="clear"></div>
                 </div>
               </div>
               </a>
            </div>`
            )
           
        });
      },
      error: alert("ngu")
      
    });
  });
});

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