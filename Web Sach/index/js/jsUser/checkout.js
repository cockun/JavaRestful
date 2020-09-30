var data = JSON.parse(sessionStorage.getItem("product")).product;
var dataObj = JSON.parse(sessionStorage.getItem("product"));
var placeAdd = document.getElementsByClassName("placeAdd")[0];
var total = document.getElementsByClassName('totalPrice')[0];
total.innerText = dataObj.total;
var discount = document.getElementsByClassName('discount')[0];
discount.innerText = dataObj.discount;
var lastPrice = document.getElementsByClassName('lastPrice')[0];
lastPrice.innerText = dataObj.lastPrice;


for (let i = 0 ; i < data.length ; i++){
    var cartRow = document.createElement('div');
    var template = `
        <div class="product">
            <div class="left">
                <div class="bold"><span class="quantity">${data[i].quantity}</span>  x  ${data[i].name}</div>
                <div class="fade">Khi đôi môi em còn đỏ mọng</div>
            </div>
            <div class="right">
                 ${Number(data[i].price)*Number(data[i].quantity)}
            </div>
        </div>
    `
    cartRow.innerHTML = template;
    cartRow.classList.add('productCont');
    placeAdd.appendChild(cartRow);
}
function addBill()
{
    
}
