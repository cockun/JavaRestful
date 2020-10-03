

var placeAdd = document.getElementsByClassName("placeAdd")[0];
$(document).ready(function () {
  var btnSearch = document.getElementById("submit");

  var tagA = document.getElementById("navSearch");

  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/products",
      success: function (datas) {
        datas.data.forEach((data) => {
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
          );
        });
      },
    });
  });
});




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

  let btnSearch = document.getElementById("submitBtn");
  let tagA = document.getElementById("navSearch");
  btnSearch.addEventListener('click' , () => {
    let value = document.getElementById("textboxSearch").value;
    tagA.setAttribute("href",`./Search.html?name=${value}`)
  })


/////////////////////
})

