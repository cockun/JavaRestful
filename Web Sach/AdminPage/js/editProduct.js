var queryDict = {}
location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]})
var idcategory;
$(document).ready(function () {
    $(function () {
      $.ajax({
        async: false,
        type: "GET",
        url: `http://localhost:8080/admin/product?id=${queryDict.id}`,
        success: function (datas) {
             let data=datas.data;
             idcategory=data.idcategory;
              $("#placeAdd").append(
                `
                <div class="contentCont">
                <div class="infoUser Left">
                    <div class="subInfo">
                        <div class="info">ID</div>
                        <input type="text" id="id" class="inputField" value="${data.id}">
                    </div>
                    <div class="subInfo">
                        <div class="info">Tên Sản Phẩm</div>
                        <input type="text" id="name" class="inputField" value="${data.name}">
                    </div>
                    <div class="subInfo">
                        <div class="info">Mã Code</div>
                        <input type="text" id="code" class="inputField" value="${data.code}">
                    </div>
                    <div class="subInfo">
                        <div class="info"> Giá Tiền</div>
                        <input type="text" id="price" class="inputField" value=${data.price}>
                    </div>
                    <div class="subInfo">
                        <div class="info">Giá Khuyến Mãi</div>
                        <input type="text" id="discount"  class="inputField" value=${data.discount}>
                    </div>
                </div>
                <div class="infoUser Right">
                    <div class="subInfo">
                        <div class="info">Tác Giả</div>
                        <input type="text" id="writer" class="inputField" value="${data.writer}">
                     </div>
                    <div class="subInfo">
                        <div class="info">Thể Loại Sách</div>
                        <input type="text" id="idcategory" class="inputField" value="${data.idcategory}">
                    </div>
                    <div class="subInfo">
                        <div class="info">Mô Tả Sách</div>
                        <input type="text" id="detail" class="inputField" value="${data.detail}">
                    </div>
                    <div class="subInfo">
                        <div class="info">Hình Ảnh</div>
                        <input type="text" id="img" class="inputField" value=${data.pic}>
                    </div>
                    <div class="btnAccept" onclick="adjustProduct()" >Xác Nhận</div>
                </div>
            </div>
    `
              )
        },
      });
    });
    

});


////////////////////// post SP


function adjustProduct()
{
    let code = document.getElementById("code").value
    let detail =document.getElementById("detail").value;
    let discount=document.getElementById("discount").value;
    let id=document.getElementById("id").value;
    let idcategory=document.getElementById("idcategory").value;
    let name = document.getElementById("name").value;
    let pic =document.getElementById("img").value;
    let price = document.getElementById("price").value;
    let writer = document.getElementById("writer").value;

    let obj={
        "code": code,
        "detail": detail,
        "discount": discount*1,
        "id": id,
        "idcategory": idcategory,
        "name": name,
        "pic": pic,
        "price": price*1,
        "writer": writer
    }
    $.ajax({
        type:"PUT",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/admin/product",
        data:JSON.stringify(obj),
        success:function(data)
        {
            alert("Success");
            console.log(data);
            window.location="/Web%20Sach/AdminPage/Product.html";
        },

    })
    
}
