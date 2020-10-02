var queryDict = {}

location.search.substr(1).split("&").forEach(function(item) {queryDict[item.split("=")[0]] = item.split("=")[1]})


$(document).ready(function () {
    $(function () {
      $.ajax({
        async: false,
        type: "GET",
        url: `http://localhost:8080/account?id=${queryDict.id}`,
        success: function (datas) {
             let data=datas.data;
              $("#placeAdd").append(
                `
                <div class="contentCont">
                <div class="infoUser Left">
                    <div class="subInfo">
                        <div class="info">ID</div>
                        <input type="text" id="id" class="inputField" value="${data.id}">
                    </div> 
                    <div class="subInfo">
                        <div class="info">Họ và tên</div>
                        <input type="text" id="name" class="inputField" value="${data.name}">
                    </div>
                    <div class="subInfo">
                        <div class="info">Số điện thoại</div>
                        <input type="text" id="phone" class="inputField" value="${data.phone}">
                    </div>
                    <div class="subInfo">
                        <div class="info"> Địa chỉ</div>
                        <input type="text" id="address" class="inputField" value=${data.address}>
                    </div>
                    
                   
                </div>
                <div class="infoUser Right">
                <div class="subInfo">
                <div class="info">Địa chỉ Email</div>
                <input type="text" id="email"  class="inputField" value=${data.email}>
            </div>
            <div class="subInfo">
           
           
            
        </div>
                    
                    
                    <div class="btnAccept" onclick="adjustAccount()" >Xác Nhận</div>
                </div>
            </div>
    `
              )
        },
      });
    });
    

});


////////////////////// post SP


function adjustAccount()
{
    let id = document.getElementById("id").value;
    let phone=document.getElementById("phone").value;
    let address=document.getElementById("address").value;
    let email=document.getElementById("email").value;
    let name =document.getElementById("name").value;
    
    let obj={
        "id": id,
        "name":name,
        "phone": phone,
        "address": address,
        "email": email,
    }
    console.log(obj);
    $.ajax({
        type:"PUT",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/account",
        data:JSON.stringify(obj),
        success:function(data)
        {
            alert("Success");
            console.log(data);
            
        },

    })
    
}

