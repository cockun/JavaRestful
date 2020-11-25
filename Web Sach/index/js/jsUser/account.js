var infoUser = document.getElementById("userInfo");
var billUser = document.getElementById("userBill");
var passUser = document.getElementById("userPass");
var placeAdd = document.getElementsByClassName("rightCont")[0];
var objData = JSON.parse(sessionStorage.getItem('userInfo'));
document.getElementsByClassName('point')[0].innerText = " "+objData.rewardPoint;

////buttonSave
var btnSaveInfo , btnSavePass;
////


///// First time login this page 
let divAdd = document.createElement("div");
  divAdd.classList.add("adJustInfo");
  let template = `
    <div class="highlight">Thông Tin Cá Nhân</div>
    <div class="info">
        <div class="title">Tên Đăng Nhập</div>
        <input type="text" class="inp loginName" value="${objData.user}" disabled>
    </div>
    <div class="info">
        <div class="title">Họ Tên</div>
        <input type="text" class="inp name" value="${objData.name}">
    </div>
    
    <div class="highlight">Thông Tin Liên Lạc</div>
    <div class="info">
        <div class="title">Địa Chỉ</div>
        <input type="text" class="inp address" value="${objData.address}">
    </div>
    <div class="info">
        <div class="title">Email</div>
        <input type="text" class="inp email" value="${objData.email}"> 
    </div>
    <div class="info">
        <div class="title">Số ĐT</div>
        <input type="text" class="inp phone" value="${objData.phone}">
    </div>
    <div id="btnSaveInfo" class="btnSave">Lưu</div>
    `;
  divAdd.innerHTML = template;
  placeAdd.appendChild(divAdd);
  btnSaveInfo = document.getElementById('btnSaveInfo')
  btnSaveInfo.addEventListener('click' , () =>{
    let dataObj ={
      address: document.getElementsByClassName('address')[0].value,
      email: document.getElementsByClassName('email')[0].value,
      id: objData.id,
      name: document.getElementsByClassName('name')[0].value,
      phone: document.getElementsByClassName('phone')[0].value,
      
    }
    $.ajax({
      type:"PUT",
      headers: { 
        
          'Content-Type': 'application/json' 
      },
      url: "http://localhost:8080/account",
      data:JSON.stringify(dataObj),
      success:function(data)
      {
        if (data.success){
          alert("Sửa Thành Công!!!");
          let objSess = {
            address: document.getElementsByClassName('address')[0].value,
            author: objData.author,
            email: document.getElementsByClassName('email')[0].value,
            id: objData.id,
            name:  document.getElementsByClassName('name')[0].value,
            phone: document.getElementsByClassName('phone')[0].value,
            rewardPoint: objData.rewardPoint,
            type: objData.type,
            user: objData.user
          }
          sessionStorage.setItem('userInfo',JSON.stringify(objSess));
          window.location="./account.html";

        }
        else{
          alert(data.message);
        }
      },
      error:function(data)
      {
          alert("Có Lỗi !!!");
          
      },
     
  })
  })
/////

infoUser.addEventListener("click", () => {
  infoUser.classList.add('clicked');
  billUser.classList.remove('clicked');
  passUser.classList.remove('clicked');

  placeAdd.innerHTML = "";
  let divAdd = document.createElement("div");
  divAdd.classList.add("adJustInfo");
  let template = `
    <div class="highlight">Thông Tin Cá Nhân</div>
    <div class="info">
        <div class="title">Tên Đăng Nhập</div>
        <input type="text" class="inp loginName" value="${objData.user}" disabled>
    </div>
    <div class="info">
        <div class="title">Họ Tên</div>
        <input type="text" class="inp name" value="${objData.name}">
    </div>
    
    <div class="highlight">Thông Tin Liên Lạc</div>
    <div class="info">
        <div class="title">Địa Chỉ</div>
        <input type="text" class="inp address" value="${objData.address}">
    </div>
    <div class="info">
        <div class="title">Email</div>
        <input type="text" class="inp email" value="${objData.email}"> 
    </div>
    <div class="info">
        <div class="title">Số ĐT</div>
        <input type="text" class="inp phone" value="${objData.phone}">
    </div>
    <div id="btnSaveInfo" class="btnSave">Lưu</div>
    `;
  divAdd.innerHTML = template;
  placeAdd.appendChild(divAdd);
  btnSaveInfo = document.getElementById('btnSaveInfo')
  btnSaveInfo.addEventListener('click' , () =>{
    let dataObj ={
      address: document.getElementsByClassName('address')[0].value,
      email: document.getElementsByClassName('email')[0].value,
      id: objData.id,
      name: document.getElementsByClassName('name')[0].value,
      phone: document.getElementsByClassName('phone')[0].value,
      
    }
    $.ajax({
      type:"PUT",
      headers: { 
        
          'Content-Type': 'application/json' 
      },
      url: "http://localhost:8080/account",
      data:JSON.stringify(dataObj),
      success:function(data)
      {
        if (data.success){
          alert("Sửa Thành Công!!!");
          let objSess = {
            address: document.getElementsByClassName('address')[0].value,
            author: objData.author,
            email: document.getElementsByClassName('email')[0].value,
            id: objData.id,
            name:  document.getElementsByClassName('name')[0].value,
            phone: document.getElementsByClassName('phone')[0].value,
            rewardPoint: objData.rewardPoint,
            type: objData.type,
            user: objData.user
          }
          sessionStorage.setItem('userInfo',JSON.stringify(objSess));
          window.location="./account.html";

        }
        else{
          alert(data.message);
        }
      },
      error:function(data)
      {
          alert("Có Lỗi !!!");
          
      },
     
  })
  })
  
});

passUser.addEventListener("click", () => {
  placeAdd.innerHTML = "";
  infoUser.classList.remove('clicked');
  billUser.classList.remove('clicked');
  passUser.classList.add('clicked');

  let divAdd = document.createElement("div");
  divAdd.classList.add("adJustInfo");
  let template = `
    <div class="highlight">Thay Đổi Mật Khẩu</div>
    <div class="info">
        <div class="title">Mật Khẩu Cũ</div>
        <input type="text" class="inp oldPass">
    </div>
    <div class="info">
        <div class="title">Mật Khẩu Mới</div>
        <input type="text" class="inp newPass">
    </div>
    <div class="info">
        <div class="title">Nhập Lại</div>
        <input type="text" class="inp renewPass">
    </div>
    <div  id="btnSavePass" class="btnSave" >Lưu</div>
    `;
  divAdd.innerHTML = template;
  placeAdd.appendChild(divAdd);
  btnSavePass = document.getElementById('btnSavePass');
  btnSavePass.addEventListener('click' , () =>{
    let passObj = {
      password: document.getElementsByClassName('oldPass')[0].value,
      passwordNew: document.getElementsByClassName('newPass')[0].value,
      user: objData.user
    }
    console.log(passObj);
    $.ajax({
      type:"PUT",
      headers: { 
        
          'Content-Type': 'application/json' 
      },
      url: "http://localhost:8080/changePassword",
      data:JSON.stringify(passObj),
      success:function(data)
      {
        if (data.success){
          alert("Sửa Thành Công!!!");
          window.location="./account.html";

        }
        else{
          alert(data.message);
        }
      },
      error:function(data)
      {
          alert("Có Lỗi !!!");
          
      },
     
  })
  })
  
});



billUser.addEventListener("click", () => {
  placeAdd.innerHTML = "";
  infoUser.classList.remove('clicked');
  billUser.classList.add('clicked');
  passUser.classList.remove('clicked');

  // let divAdd = document.createElement("div");
  // divAdd.classList.add("adJustInfo");
  // let template = `
  //   <div class="highlight">Quản Lý Đơn Hàng</div>
  //   <div class="adJustInfo">
  //   <div class="tableHeader">
  //     <div class="Content">Mã Bill</div>
  //     <div class="Content">Ngày Mua</div>
  //     <div class="Content">Tổng Tiền</div>
  //     <div class="Content">Xem Chi Tiết</div>
  //   </div>
  //   <!-- <div class="tableHeader">
  //     <div class="Content billCode">Mã Bill</div>
  //     <div class="Content billDate">Ngày Mua</div>
  //     <div class="Content billPrice">Tổng Tiền</div>
  //     <div class="Content billDetail">Xem Chi Tiết</div>
  //   </div> -->
  // </div>
  //   `;
  // divAdd.innerHTML = template;
  // placeAdd.appendChild(divAdd);
});

///////////////////// PROCESSING

