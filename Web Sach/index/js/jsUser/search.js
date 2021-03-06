var queryDict = {}
location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]});

var placeAdd = document.getElementsByClassName("placeAdd")[0];
var quantity = document.getElementById("quantity");
var dataPost ={
    "field": "name",
    "value": queryDict.value
}

let btnSearch = document.getElementById("submitBtn");
let tagA = document.getElementById("navSearch");
btnSearch.addEventListener("click", () => {
  let value = document.getElementById("textboxSearch").value;
  console.log(value);
  tagA.setAttribute("href", `./Search.html?name=${value}`);
});

$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/search/products?field=name&value=${queryDict.name}`,
      headers: { 
        'Content-Type': 'application/json' 
      },
      crossDomain: true,
      success: function (datas) {
        
        quantity.innerText = datas.data.length;
        datas.data.forEach(data => {
            console.log(data);
            $("#placeAdd").append(
              `
              <div class="container">
              <div class="pdImg">
                <div class="imgCont">
                  <div class="background">
                    <div class="searchPd">
                      <a href="single.html?id=${data.id}"
                        ><i class="fas fa-search"></i
                      ></a>
                    </div>
                  </div>
                  <img src="${data.pic}" alt="" class="imgPd" />
                </div>
              </div>
              <div class="pdInfo">
                <div class="pdInfoSubCont">
                  <div class="pdName">${data.name}</div>
                  <div class="pdCate">${data.idcategory}</div>
                  <div class="pdPrice">
                    ${formatDollar(data.discount*1)}<span>đ</span>
                  </div>
                </div>
              </div>
            </div>
              ` 
            )
        });
      },
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