var queryDict = {}
location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]})
var idcategory;
$(document).ready(function () {
    let idSupplier ;
    let idCategory ;

    $(function () {
      $.ajax({
        async: false,
        type: "GET",
        url: `http://localhost:8080/admin/product?id=${queryDict.id}`,
        success: function (datas) {
            
             let data=datas.data;
             console.log(data)
             idSupplier = data.idSupplier;
             idCategory= data.idcategory;
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
                    <div class="subInfo">
                        <div class="info">Giá Nhập</div>
                        <input type="text" id="root"  class="inputField" value=${data.rootprice}>
                    </div>
                </div>
                <div class="infoUser Right">
                    <div class="subInfo">
                        <div class="info">Tác Giả</div>
                        <input type="text" id="writer" class="inputField" value="${data.writer}">
                     </div>
                     
                     <div class="form-group">
                       <label for="formGroupExampleInput">Thể loại</label>
                       <select class="form-control" id="categoryCode">
                        
                       
                       </select>
                     </div>
                     <div class="form-group">
          <label for="formGroupExampleInput">Nhà cung cấp</label>
          <select class="form-control" id="supplierCode">
           
          
          </select>
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
      }).then(()=>{
        callApi("GET","categories").data.forEach(category => {
            $("#categoryCode").append(
              `<option value =${category.id}>${category.name}</option>`
            )
          })
          callApi("GET","admin/supplier").data.forEach(supplier => {
            $("#supplierCode").append(
              `<option value =${supplier.id}>${supplier.name}</option>`
            )   
          })
          console.log(idCategory);
          console.log(idSupplier);
            $("#categoryCode").val(idCategory) ;
            $("#supplierCode").val(idSupplier) ;
          
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
    let idcategory=document.getElementById("categoryCode").value;
    let name = document.getElementById("name").value;
    let pic =document.getElementById("img").value;
    let price = document.getElementById("price").value;
    let writer = document.getElementById("writer").value;
    let idsup = document.getElementById("supplierCode").value;
    let rootprice =document.getElementById("root").value;

    let obj={
        "code": code,
        "detail": detail,
        "discount": discount*1,
        "id": id,
        "idcategory": idcategory,
        "idSupplier": idsup,
        "name": name,
        "pic": pic,
        "price": price*1,
        "rootprice":rootprice*1,
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
            alert(data.message);
            window.location('./Product.html')
          
        },

    })
    
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
