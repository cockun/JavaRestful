var infoUser = document.getElementById("userInfo");
var billUser = document.getElementById("userBill");
var passUser = document.getElementById("userPass");
var placeAdd = document.getElementsByClassName("rightCont")[0];
;
var objData = JSON.parse(sessionStorage.getItem('userInfo'));
var modal = document.getElementById('myModal');

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
  let placeAddInfo = document.getElementsByClassName("rightCont")[0];
  infoUser.classList.add('clicked');
  billUser.classList.remove('clicked');
  passUser.classList.remove('clicked');

  placeAddInfo.innerHTML = "";
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
  placeAddInfo.appendChild(divAdd);
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
  let placeAddPass = document.getElementsByClassName("rightCont")[0];

  placeAddPass.innerHTML = "";
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
  placeAddPass.appendChild(divAdd);
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
  let placeAddBill = document.getElementsByClassName("rightCont")[0];
  placeAddBill.innerHTML = "";
  let addModal = document.createElement("div");
  let modalTemp = ` <div id="myModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Chi Tiết Đơn Hàng</h2>
    </div>
    <div class="modal-body">
      <div class="row">
        <div class="rowInfo bold">Tên Sản Phẩm</div>
        <div class="rowInfo bold">Số Lượng</div>
        <div class="rowInfo bold">Đơn Giá</div>
        <div class="rowInfo bold">Thành Tiền</div>
      </div>
      
    </div>
    </div>
   </div>`;
  addModal.innerHTML=modalTemp;
  placeAddBill.appendChild(addModal);


  infoUser.classList.remove('clicked');
  billUser.classList.add('clicked');
  passUser.classList.remove('clicked');

  let divAdd = document.createElement("div");
  divAdd.classList.add("adJustInfo");
  let template = `
  <div class="highlight">Quản Lý Đơn Hàng</div>
    <div class="tableHeader">
      <div class="Content">Mã Bill</div>
      <div class="Content">Ngày Mua</div>
      <div class="Content">Tổng Tiền</div>
      <div class="Content">Xem Chi Tiết</div>
    </div>

    `;
  divAdd.innerHTML = template;
  placeAddBill.appendChild(divAdd);
  $.ajax({
    type:"GET",
    headers: { 
      
        'Content-Type': 'application/json' 
    },
    url: `http://localhost:8080/bill/user?user=${objData.user}`,
    success:function(data)
    {
      if (data.success){
        data.data.forEach(item => {
        let divAdd = document.createElement("div");
        divAdd.classList.add("tableHeader");
        let template = `
      
            <div class="Content billCode">${item.code}</div>
            <div class="Content">${item.date}</div>
            <div class="Content">${item.total}</div>
            <div class="Content" ><div class="viewDetail">Xem</div></div>
          `;
          
          divAdd.innerHTML=template;
          placeAddBill.appendChild(divAdd);

          ////
         
      });
      
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
  window.setTimeout(()=>{
    let btnView = document.getElementsByClassName('viewDetail');
    let modal = document.getElementById("myModal");
    
    const viewDetail = (e) => {
      let placeAdd = document.getElementsByClassName('modal-body')[0];
      placeAdd.innerHTML=`      <div class="row">
            <div class="rowInfo bold first">Tên Sản Phẩm</div>
            <div class="rowInfo bold">Số Lượng</div>
            <div class="rowInfo bold">Đơn Giá</div>
            <div class="rowInfo bold">Thành Tiền</div>
          </div>`
      let getCode = e.target.parentElement.parentElement.getElementsByClassName("billCode")[0].innerText;
      $.ajax({
        type:"GET",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: `http://localhost:8080/bill/code?code=${getCode}`,
        success:function(data)
        {
          if (data.success){
            data.data.listBillInfoRes.forEach(item => {
           
            
            let divAdd = document.createElement("div");
            divAdd.classList.add("row");
            let template = `
          
            <div class="rowInfo first">${item.nameProduct}</div>
            <div class="rowInfo">${item.quantity}</div>
            <div class="rowInfo">${item.discount}</div>
            <div class="rowInfo">${item.total}</div>
              `;
              
              divAdd.innerHTML=template;
              placeAdd.appendChild(divAdd);
    
              ////
             
          });
          
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
      modal.style.display="block";
      let btnClose = document.getElementsByClassName('close')[0];
      btnClose.addEventListener('click' , () =>{
        modal.style.display="none";
      })
    }
    
    for (let i = 0 ; i < btnView.length ; i++){
     
      btnView[i].addEventListener('click' , viewDetail);
    
    }
  },1000)

  
  
});

///////////////////// PROCESSING

