var queryDict = {};
location.search
  .substr(1)
  .split("&")
  .forEach(function (item) {
    queryDict[item.split("=")[0]] = item.split("=")[1];
  });
var idcategory;
$(document).ready(function () {
  $(function () {
    $.ajax({
      async: false,
      type: "GET",
      url: `http://localhost:8080/admin/supplier?id=${queryDict.id}`,
      success: function (datas) {
        let data = datas.data;
        console.log(data);
        $("#placeAdd").append(
          `
                <div class="contentCont">
          <div class="infoUser Left">
              <div class="subInfo">
                  <div class="info">Tên nhà cung cấp</div>
                  <input type="text" id="name" class="inputField" value="${data[0].name}">
              </div>
              <div class="subInfo">
                  <div class="info">Địa chỉ</div>
                  <input type="text" id="address" class="inputField" value="${data[0].address}">
              </div>
              <div class="subInfo">
                  <div class="info"> Số điện thoại</div>
                  <input type="text" id="phone" class="inputField" value="${data[0].phone}">
              </div>
              
              <div class="btnAccept" >Xác Nhận</div>
          </div>
          <div class="infoUser Right">
             
          </div>
      </div>
                
           
    `
        );
      },
    });
  });
});
