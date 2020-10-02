var queryDict = {}
location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]})
var placeAdd = document.getElementById('placeAdd');
var getCategory="";

$(document).ready(function () {
    $(function () {
      $.ajax({
        async: false,
        type: "GET",
        url: `http://localhost:8080/product?id=${queryDict.id}`,
        success: function (datas) {
            getCategory=datas.data.idcategory;
            console.log(getCategory);
             let data=datas.data;
              console.log(data.pic);
              $("#placeAdd").append(
                `
    <div>
        <div class="grid images_3_of_2">
 
                <img class="pdImg" src="${data.pic}"  />

            <div class="clearfix"></div>
        </div>
        <div class="desc1 span_3_of_2">
            <h3 class="m_3 pdName">${data.name}</h3>
            <p class="m_5 pdPrice">${data.discount}
            <div class="btn_form">
            <div>
                <input type="submit" value="Thêm Giỏ Hàng" title="" id="btnBuy">
            </div>
            </div>
            <span class="m_link"><a href="login.html"></a> </span>
            <p class="text2">${data.detail}</p>
        </div> 
    </div>
    `
              )
        },
      });
    });
    

});



///////////////////////////////////////////
window.addEventListener('DOMContentLoaded', (event) => {
    var btnBuy = document.getElementById('btnBuy');
    var pdName = document.getElementsByClassName('pdName')[0].innerText;
    var pdPrice = document.getElementsByClassName('pdPrice')[0].innerText;
    var pdImg = document.getElementsByClassName('pdImg')[0].getAttribute('src');

const adđSessionProduct = () =>{
    alert("Thêm vào giỏ hàng thành công");
    if (sessionStorage.getItem("product") === null) {
      //  console.log(1);
        let pdObj = {
            'idUser': "Thiện Nghi",
            'length': 0,
            'total': 0,
            'discount': 0,
            'lastPrice':0,
            'product': []
        }
        sessionStorage.setItem("product",JSON.stringify(pdObj)); // khởi tạo
    }   


    let temp= JSON.parse(sessionStorage.getItem("product"));
    for (let i = 0 ; i < temp.product.length ; i++){
        if (queryDict.id === temp.product[i].id){
            temp.length++;
            temp.product[i].quantity++;
            temp.total =Number(temp.total)+Number(temp.product[i].price);
            temp.lastPrice = Number(temp.total)+Number(temp.discount);
            sessionStorage.setItem("product",JSON.stringify(temp));
            return;
        }
    }
    let pdObj = {
        'id': queryDict.id,
        'name': pdName,
        'price': pdPrice,
        'img': pdImg,
        'quantity': 1,
        'idcategory':getCategory
    }
    temp.length++;
    temp.total = temp.total+pdPrice*1;
    temp.lastPrice = Number(temp.total)+Number(temp.discount);
    temp.product.push(pdObj);
    sessionStorage.setItem("product",JSON.stringify(temp));
    
    return;
}

btnBuy.addEventListener('click',adđSessionProduct);

///////////get Suggest
$(function () {
    $.ajax({
      async: false,
      type: "GET",
      url: `http://localhost:8080/search/productsByNameCate?value=${getCategory}`,
      success: function (datas) {
           let data=datas.data;
            console.log(data);
            data.forEach((data) => {
                $("#flexiselDemo3").append(
                    `
                    <li><img src=${data.pic} /><a  href="./single.html?id=${data.id}">${data.name}</a><p>${data.discount}</p></li>
                  `
                  )
            })
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