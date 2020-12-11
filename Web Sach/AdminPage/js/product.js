$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/admin/products",
      success: function (datas) {
        datas.data.forEach((data, index) => {
         
          $("#placeAdd").append(
            ` <div class="productCont">
            <div class="productInfo pdSTT" >
                ${index + 1}
            </div>
            <div class="productInfo pdImgCont" >
                <img src=${data.pic} alt="" class="pdImg">
            </div>
            <div class="productInfo pdName">${data.name}</div>
            <div class="productInfo pdPrice">${formatDollar(data.price)} đ</div>
            <div class="productInfo pdDiscount">${formatDollar(data.discount)} đ</div>
            <div class="productInfo pdPrice">${formatDollar(data.rootprice)} đ</div>
            <div class="productInfo pdDetail">${data.detail}</div>
            <div class="productInfo pdAction">
                <a href="EditProduct.html?id=${
                  data.id
                }"><div class="bt edit">Sửa</div></a>
                <div class="bt remove" getID=${
                  data.id
                } onclick="removeProduct(this)">Xóa</div>
            </div>
        </div>`
          );
        });
      },
    });
  });
});


$("#Ssubmit").on("click",()=>{
  let obj = {
    field:"name",
    value: $("#ipSearch").val(),
    optionSort:true,
    fieldSort:"price",
    page:1,
    limit:10
  }
  $("#placeAdd").empty();
  if($("#ipSearch").val() == ""){
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/admin/products",
      success: function (datas) {
        datas.data.forEach((data, index) => {
         
          $("#placeAdd").append(
            ` <div class="productCont">
            <div class="productInfo pdSTT" >
                ${index + 1}
            </div>
            <div class="productInfo pdImgCont" >
                <img src=${data.pic} alt="" class="pdImg">
            </div>
            <div class="productInfo pdName">${data.name}</div>
            <div class="productInfo pdPrice">${formatDollar(data.price)} đ</div>
            <div class="productInfo pdDiscount">${formatDollar(data.discount)} đ</div>
            <div class="productInfo pdPrice">${formatDollar(data.rootprice)} đ</div>
            <div class="productInfo pdDetail">${data.detail}</div>
            <div class="productInfo pdAction">
                <a href="EditProduct.html?id=${
                  data.id
                }"><div class="bt edit">Sửa</div></a>
                <div class="bt remove" getID=${
                  data.id
                } onclick="removeProduct(this)">Xóa</div>
            </div>
        </div>`
          );
        });
      },
    });

  }else{
    callApi("GET","product/paginate",obj).data.forEach((data,index)=>{
      $("#placeAdd").append(
        ` <div class="productCont">
        <div class="productInfo pdSTT" >
            ${index + 1}
        </div>
        <div class="productInfo pdImgCont" >
            <img src=${data.pic} alt="" class="pdImg">
        </div>
        <div class="productInfo pdName">${data.name}</div>
        <div class="productInfo pdPrice">${formatDollar(data.price)} đ</div>
        <div class="productInfo pdDiscount">${formatDollar(data.discount)} đ</div>
        <div class="productInfo pdPrice">${formatDollar(data.rootprice)} đ</div>
        <div class="productInfo pdDetail">${data.detail}</div>
        <div class="productInfo pdAction">
            <a href="EditProduct.html?id=${
              data.id
            }"><div class="bt edit">Sửa</div></a>
            <div class="bt remove" getID=${
              data.id
            } onclick="removeProduct(this)">Xóa</div>
        </div>
      </div>`
    );
    })
  }
  


})
  



  
    

function removeProduct(event) {
  event=window.event;
  if (confirm("Bạn có chắc muốn xóa sản phẩm này không?")) {
      $.ajax({
        type:"DELETE",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: `http://localhost:8080/admin/product?id=${event.target.getAttribute("getID")}`,
        success:function(data)
        {
            alert("Success");
            console.log(data);
            window.location="/Web%20Sach/AdminPage/Product.html";
        },
    })
  } else {
    return;
  }
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