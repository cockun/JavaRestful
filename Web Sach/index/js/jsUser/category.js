var queryDict = {};
location.search
  .substr(1)
  .split("&")
  .forEach(function (item) {
    queryDict[item.split("=")[0]] = item.split("=")[1];
  });

$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: `http://localhost:8080/search/products?field=category&value=${queryDict.id}`,
      success: function (datas) {
        let nameCate = datas.data[0].idcategory;
        let title = document.getElementsByClassName("nowTitle");
        title[0].innerText = nameCate;
        title[1].innerText = "Sách " + nameCate;
        let getCate = document.getElementsByClassName("checked");
        console.log(getCate);
        for (let i = 0; i < getCate.length; i++) {
          if (getCate[i].getAttribute("value") === nameCate) {
            getCate[i].classList.add("show");
          } else {
            getCate[i].classList.remove("show");
          }
        }

        datas.data.forEach((data) => {
          $(".listBook").append(
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
                    ${formatDollar(data.discount * 1)}<span>đ</span>
                  </div>
                </div>
              </div>
            </div>
              `
          );
        });
      },
    });

    $(".listCates").append(`
    <div class="Cate" id="decu" onClick ={coc()}>
      <div class="nameCate" >  <p  >Đề cử</p></div>
    </div>
  `);

    $.ajax({
      type: "GET",
      url: `http://localhost:8080/categories`,
      success: function (data) {
        data.data.forEach((category) => {
          $(".listCates").append(`
              <div class="Cate">
                <i class="fas fa-check checked" value="${category.name}" style="flex: 1;"></i>
                <div class="nameCate" ><a href="category.html?id=${category.name}" >${category.name}</a></div>
              </div>
            `);
        });
      },
    });
  });
});

function coc() {
  console.log("A");
  obj = {
    field: "point",
    value: 4.3,
  };
  $(".listBook").empty();
  callApi("GET", "product/paginate", obj).data.forEach((data) => {
    $(".listBook").append(
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
              ${formatDollar(data.discount * 1)}<span>đ</span>
            </div>
          </div>
        </div>
      </div>
        `
    );
  });
}

console.log(document.getElementsByClassName("Cate"));
/// checklogin
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
});

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
////
