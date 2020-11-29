
var placeAdd = document.getElementsByClassName("placeAdd")[0];
$(document).ready(function () {
  var btnSearch = document.getElementById("submit");

  var tagA = document.getElementById("navSearch");

  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/products",
      success: function (datas) {
        let getData=datas.data.slice(0,10);
     
        getData.forEach((data) => {
          $("#placeAdd").append(
            `
            <div class="container">
              <div class="pdImg">
                <div class="imgCont">
                  <div class="background">
                    <div class="searchPd">
                      <a href="single.html?id=${data.id}"><i class="fas fa-search"></i></a>
                    </div>
                  </div>
                  <img src="${data.pic}" alt="" class="imgPd" />
                </div>
              </div>
              <div class="pdInfo">
                <div class="pdInfoSubCont">
                  <div class="pdName">${data.name}</div>
                  <div class="pdCate">${data.idcategory}</div>
                  <div class="pdPrice">${formatDollar(data.discount*1)}<span>đ</span></div>
                </div>
              </div>
          </div>
            `
          );
        });
        getData=datas.data.slice(10,20);
        getData.forEach((data) => {
          $("#placeAddSale").append(
            `
            <div class="container">
              <div class="pdImg">
                <div class="imgCont">
                  <div class="background">
                    <div class="searchPd">
                    <a href="single.html?id=${data.id}"><i class="fas fa-search"></i></a>
                    </div>
                  </div>
                  <img src="${data.pic}" alt="" class="imgPd" />
                </div>
              </div>
              <div class="pdInfo">
                <div class="pdInfoSubCont">
                  <div class="pdName">${data.name}</div>
                  <div class="pdCate">${data.idcategory}</div>
                  <div class="pdPrice">${formatDollar(data.discount*1)}<span>đ</span></div>
                </div>
              </div>
          </div>
            `
          );
        });
      },
    });
    $.ajax({
      type:"GET",
      async:true,
      url:`http://localhost:8080/categories`,
      success:function (data) {
        data.data.forEach((category,index)=>{
              $(".megapanel").append(`
            <div class="row">
              <div class="col1">
                <div class="h_nav">
                  <ul>
                    <li><a href="category.html?id=${category.name}" >${category.name}</a></li>
                  </ul>	
                </div>							
              </div>
            </div>
            `)
         
    
        })
      }
    })
  });
});

function logOut() {
  sessionStorage.removeItem("userInfo");
  return;
}

window.addEventListener("DOMContentLoaded", (event) => {
  if (sessionStorage.getItem("userInfo") !== null) {
    document.getElementById("button").innerHTML = `
      <div id="button">
        <i class="far fa-handshake" ></i><li style="font-size:15px; margin: 0 10px; font-weight:bold">Xin chào ${
          JSON.parse(sessionStorage.getItem("userInfo")).name
        }</li> 
        <i class="fas fa-sign-out-alt" id="logoutButton"></i><li><a onclick="logOut()" href="./index.html">Đăng Xuất</a></li>
      </div>  
    `;
  } else {
    document.getElementById("button").innerHTML = `
  <div id="button">
  <i class="fas fa-user" id="loginButton"></i><li><a href="login.html">Đăng Nhập</a></li> 
  <i class="fas fa-pen" id="registerButton"></i><li><a href="register.html">Đăng Ký</a></li>
  
  </div>  
  `;
  }

  let btnSearch = document.getElementById("submitBtn");
  let tagA = document.getElementById("navSearch");
  btnSearch.addEventListener("click", () => {
    let value = document.getElementById("textboxSearch").value;
    tagA.setAttribute("href", `./Search.html?name=${value}`);
  });



 
  

  /////////////////////
});
