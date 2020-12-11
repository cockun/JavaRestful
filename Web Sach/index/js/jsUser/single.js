var queryDict = {}
location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]})
var placeAdd = document.getElementById('placeAdd');
var getCategory="";
var getPrice="";
var total = 0;
var count = 0;
$(document).ready(function () {
    $(function () {
      $.ajax({
        async: false,
        type: "GET",
        url: `http://localhost:8080/product?id=${queryDict.id}`,
        success: function (datas) {
            getCategory=datas.data.idcategory;

             let data=datas.data;
             getPrice = data.discount;
              console.log(data.pic);
              sessionStorage.setItem("quanity", data.inStorage);
              $("#placeAdd").append(
                `
    <div>
        <div class="grid images_3_of_2">
 
                <img class="pdImg" src="${data.pic}"  />

            <div class="clearfix"></div>
        </div>
        <div class="desc1 span_3_of_2">
            <a href="./index.html" class="direct" >Home / </a><text class="direct">${data.name}</text>
            <h3 class="pdName">${data.name}</h3>
            <p class="detail">${data.detail}</p>
            <p class="quanity"> Số lượng còn tồn kho: ${data.inStorage}</p>
            <div class="rating_container">
            <div class="reviews">
        
              <div class="contentReview">
                <div class="rateInfo" style="margin-top: 25px">(<span class="avgPoint"></span> - <span class="count"></span> lượt đánh giá)</div>
                <div class="rate">
                                  <input type="radio" id="star5" name="rateTotal" value="5" class="star rateTotal" onclick="getReward()" disabled/>
                                  <label for="star5" title="text">5 stars</label>
                                  <input type="radio" id="star4" name="rateTotal"  value="4" class="star rateTotal" onclick="getReward()" disabled/>
                                  <label for="star4" title="text">4 stars</label>
                                  <input type="radio" id="star3" name="rateTotal"  value="3" class="star rateTotal" onclick="getReward() " disabled />
                                  <label for="star3" title="text">3 stars</label>
                                  <input type="radio" id="star2" name="rateTotal"  value="2" class="star rateTotal" onclick="getReward() " disabled/>
                                  <label for="star2" title="text">2 stars</label>
                                  <input type="radio" id="star1" name="rateTotal"  value="1" class="star rateTotal" onclick="getReward() " disabled/>
                                  <label for="star1" title="text">1 star</label>
                </div>
              
              </div>
            </div> 

            <p class="m_5 pdPrice">${formatDollar(data.discount*1)} đ
            
            <div class="btn_form">
            <div>
                <input type="submit" value="Thêm Giỏ Hàng" title="" id="btnBuy">
            </div>
            </div>
            <span class="m_link"><a href="login.html"></a> </span>
            
       
    </div>
    `
              )
        },
      });
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
    $.ajax({
      type:"GET",
      async:true,
      url:`http://localhost:8080/review?field=idProduct&value=${queryDict.id}`,
      success:function (data) {
        count = data.data.length;
        data.data.forEach((rate,index)=>{
          
              $(".reviewCont").append(`
              <div class="reviews">
              <div class="nameUserReview">${rate.nameUser}</div>
              <div class="contentReview">
                <div class="rate">
                                  <input type="radio" id="star5" name="${"rate"+index}" value="5" class="${"star rate"+index}" onclick="getReward()" disabled/>
                                  <label for="star5" title="text">5 stars</label>
                                  <input type="radio" id="star4" name="${"rate"+index}" value="4" class="${"star rate"+index}" onclick="getReward()" disabled/>
                                  <label for="star4" title="text">4 stars</label>
                                  <input type="radio" id="star3" name="${"rate"+index}" value="3" class="${"star rate"+index}" onclick="getReward() " disabled />
                                  <label for="star3" title="text">3 stars</label>
                                  <input type="radio" id="star2" name="${"rate"+index}" value="2" class="${"star rate"+index}" onclick="getReward() " disabled/>
                                  <label for="star2" title="text">2 stars</label>
                                  <input type="radio" id="star1" name="${"rate"+index}" value="1" class="${"star rate"+index}" onclick="getReward() " disabled/>
                                  <label for="star1" title="text">1 star</label>
                </div>
                <div class="comment">${rate.comment}</div>
              </div>
            </div>
            `)
         
            let point = parseInt(rate.reviewPoint);
            total += point;
        
            let getStar = document.getElementsByClassName(`${"rate"+index}`);
            getStar[5-point].checked = true;
        })


        let avgPoint = Math.round( (parseFloat(total*1 / count * 1 )) * 100) / 100;
        if(count === 0){
          document.getElementsByClassName('rateInfo')[0].innerText="Chưa có lượt đánh giá!";
        }
        else{
          document.getElementsByClassName('avgPoint')[0].innerText=avgPoint+"đ";
          document.getElementsByClassName('count')[0].innerText=count;
        }
      
        let getRateTotal = document.getElementsByClassName('rateTotal');
        if( avgPoint % parseInt(avgPoint % 10) >= 0.5) {
          avgPoint = parseInt(avgPoint)+1;
          getRateTotal[5-avgPoint].checked=true;
        }
        else{
          avgPoint = parseInt(avgPoint);
          getRateTotal[5-avgPoint].checked=true;
        }
      }
    })


})



///////////////////////////////////////////
window.addEventListener('DOMContentLoaded', (event) => {
    var btnBuy = document.getElementById('btnBuy');
    var pdName = document.getElementsByClassName('pdName')[0].innerText;
    var pdPrice = getPrice;
    var pdImg = document.getElementsByClassName('pdImg')[0].getAttribute('src');

    
const adđSessionProduct = () =>{
  if(sessionStorage.getItem("quanity")>0)
  {
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
  else
  {
    alert("Không đủ hàng tồn kho")
  }
    
}

btnBuy.addEventListener('click',adđSessionProduct);

///////////get Suggest
$(function () {
    $.ajax({
      async: false,
      type: "GET",
      url: `http://localhost:8080/search/products?field=category&value=${getCategory}`,
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