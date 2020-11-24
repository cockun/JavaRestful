var infoUser = document.getElementById("userInfo");
var billUser = document.getElementById("userBill");
var passUser = document.getElementById("userPass");
console.log(infoUser);
var placeAdd = document.getElementsByClassName("rightCont")[0];
var objData = JSON.parse(sessionStorage.getItem('userInfo'));
console.log(objData);
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
        <input type="text" class="inp loginName" value="${objData.name}">
    </div>
    
    <div class="highlight">Thông Tin Liên Lạc</div>
    <div class="info">
        <div class="title">Địa Chỉ</div>
        <input type="text" class="inp loginName" value="${objData.address}">
    </div>
    <div class="info">
        <div class="title">Email</div>
        <input type="text" class="inp loginName" value="${objData.email}"> 
    </div>
    <div class="info">
        <div class="title">Số ĐT</div>
        <input type="text" class="inp loginName" value="${objData.phone}">
    </div>
    <div class="btnSave">Lưu</div>
    `;
  divAdd.innerHTML = template;
  placeAdd.appendChild(divAdd);
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
        <input type="text" class="inp loginName">
    </div>
    <div class="info">
        <div class="title">Mật Khẩu Mới</div>
        <input type="text" class="inp loginName">
    </div>
    <div class="info">
        <div class="title">Nhập Lại</div>
        <input type="text" class="inp loginName">
    </div>
    <div class="btnSave">Lưu</div>
    `;
  divAdd.innerHTML = template;
  placeAdd.appendChild(divAdd);
});
