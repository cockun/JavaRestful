var data = JSON.parse(sessionStorage.getItem("product")).product;
var placeAdd = document.getElementsByClassName("placeAdd")[0];

for (let i = 0 ; i < data.length ; i++){
    var cartRow = document.createElement('div');
    var template = `
            <div class="productInfo pdImgCont">
                <img src="${data[i].img}" alt="" class="pdImg">
            </div>
            <div class="productInfo pdName">${data[i].name}</div>
            <div class="productInfo pdPrice">${data[i].price}vnđ</div>
            <div class="productInfo pdQuantity">${data[i].quantity}
                <div class="adjustQuantity">
                    <div class="option">+</div>
                    <div class="option">-</div>
                </div>
            </div>
            <div class="productInfo pdTotal">${(data[i].price)}</div>
            <div class="productInfo pdRemove">
                <img src="./images/delete.png" alt="imgDel" class="imgDel">
            </div>
    `
    cartRow.innerHTML = template;
    cartRow.classList.add('productCont');
    placeAdd.appendChild(cartRow);
}
