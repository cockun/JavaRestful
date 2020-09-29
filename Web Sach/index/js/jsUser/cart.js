var data = JSON.parse(sessionStorage.getItem("product")).product;
var dataObj = JSON.parse(sessionStorage.getItem("product"));
var placeAdd = document.getElementsByClassName("placeAdd")[0];

for (let i = 0 ; i < data.length ; i++){
    var cartRow = document.createElement('div');
    var template = `
            <div class="productInfo pdImgCont" >
                <img src="${data[i].img}" alt="" class="pdImg">
            </div>
            <div class="productInfo pdName">${data[i].name}</div>
            <div class="productInfo pdPrice">${data[i].price}vnđ</div>
            <div class="productInfo pdQuantity"><p id="quantity">${data[i].quantity}</p>
                <div class="adjustQuantity" >
                    <div class="option increase">+</div>
                    <div class="option decrease">-</div>
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

////////////////////////////

var btnIncrease = document.getElementsByClassName('increase')[0];
var btnDecrease = document.getElementsByClassName('decrease')[0];
var btnRemove   = document.getElementsByClassName('imgDel')[0];
var quantity    = document.getElementById('quantity');
var total       = document.getElementsByClassName

const adjustQuantity = (e) => {
    let getParent = e.target.parentElement.parentElement.parentElement;
    let getName = getParent.getElementsByClassName('pdName')[0].innerText;
    for (let i = 0 ; i < data.length ; i++){
        if (data[i].name == getName){
            e.target.innerText ==="+" ? dataObj.product[i].quantity++ :dataObj.product[i].quantity--;
            e.target.innerText ==="+" ? dataObj.length++ :dataObj.length--;
            dataObj.product[i].quantity === 0 ? dataObj.product[i].quantity = 1 : dataObj.product[i].quantity; 
            quantity.innerText=dataObj.product[i].quantity;
        }
    }
    sessionStorage.setItem("product",JSON.stringify(dataObj));
}

const removeProduct = (e) => {
    let getParent = e.target.parentElement.parentElement.parentElement;
    let getName = getParent.getElementsByClassName('pdName')[0].innerText;
    for (let i = 0 ; i < data.length ; i++){
        if (data[i].name == getName){
            dataObj.length -= dataObj.product[i].quantity;
            dataObj.product.splice(i,1);
        }
    }
    getParent.remove();
    sessionStorage.setItem("product",JSON.stringify(dataObj));

}
btnIncrease.addEventListener('click', adjustQuantity);
btnDecrease.addEventListener('click', adjustQuantity);
btnRemove.addEventListener('click' , removeProduct);

///////