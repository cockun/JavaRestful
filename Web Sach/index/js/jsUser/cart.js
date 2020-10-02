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
            <div class="productInfo pdPrice">${formatDollar(data[i].price*1)}vnđ</div>
            <div class="productInfo pdQuantity"><p class="quantity">${data[i].quantity}</p>
                <div class="adjustQuantity" >
                    <div class="option increase">+</div>
                    <div class="option decrease">-</div>
                </div>
            </div>
            <div class="productInfo pdTotal"><p class="pdTotalValue">${formatDollar(data[i].price*1*data[i].quantity*1)} vnđ</p></div>
            <div class="productInfo pdRemove">
                <img src="./images/delete.png" alt="imgDel" class="imgDel">
            </div>
    `
    cartRow.innerHTML = template;
    cartRow.classList.add('productCont');
    placeAdd.appendChild(cartRow);
}

////////////////////////////

var btnIncrease = document.getElementsByClassName('increase');
var btnDecrease = document.getElementsByClassName('decrease');
var btnRemove   = document.getElementsByClassName('imgDel');    

var total       = document.getElementsByClassName('getTotal')[0];
var discount    = document.getElementsByClassName('getDiscount')[0];
var lastPrice   = document.getElementsByClassName('getLastPrice')[0];

//////////////////////////////
const adjustQuantity = (e) => {
    let getParent = e.target.parentElement.parentElement.parentElement;
    let getName = getParent.getElementsByClassName('pdName')[0].innerText;
    let totalProduct = getParent.getElementsByClassName('pdTotalValue')[0];
    let quantity = getParent.getElementsByClassName('quantity')[0];
    for (let i = 0 ; i < data.length ; i++){
        if (data[i].name == getName){
            if(e.target.innerText ==="+"){
                dataObj.product[i].quantity++ ;
                dataObj.length++;
                dataObj.total = (Number(dataObj.total) + Number(dataObj.product[i].price));
                dataObj.lastPrice = (Number(dataObj.total) + Number(dataObj.discount));
                totalProduct.innerText = formatDollar(Number(dataObj.product[i].price) * Number(dataObj.product[i].quantity)) +"vnđ"; 
            }
            else{
                if(dataObj.product[i].quantity !== 1){
                    dataObj.product[i].quantity--;
                    dataObj.length--;
                    dataObj.total = (Number(dataObj.total) - Number(dataObj.product[i].price)) ;
                    dataObj.lastPrice = (Number(dataObj.total) - Number(dataObj.discount)) ;
                    totalProduct.innerText = formatDollar(Number(dataObj.product[i].price) * Number(dataObj.product[i].quantity)) +"vnđ"; 
                }
            } 
            // if(dataObj.product[i].quantity === 0){
            //     dataObj.product[i].quantity = 1 ;
            //     dataObj.length = 1;
            // } 
            quantity.innerText=dataObj.product[i].quantity;
        }
    }

    sessionStorage.setItem("product",JSON.stringify(dataObj));
    updateCart();
}

const removeProduct = (e) => {
    let getParent = e.target.parentElement.parentElement;
    let getName = getParent.getElementsByClassName('pdName')[0].innerText;
    for (let i = 0 ; i < data.length ; i++){
        if (data[i].name == getName){
            dataObj.length -= dataObj.product[i].quantity;
            dataObj.total = Number(dataObj.total) - Number(dataObj.product[i].price)*Number(dataObj.product[i].quantity);
            dataObj.lastPrice = Number(dataObj.total) + Number(dataObj.discount);
            dataObj.product.splice(i,1);
        }
    }
    getParent.remove();
    sessionStorage.setItem("product",JSON.stringify(dataObj));
    updateCart();

}
for (let i = 0 ; i < btnIncrease.length ; i++){
    btnIncrease[i].addEventListener('click', adjustQuantity);
    btnDecrease[i].addEventListener('click', adjustQuantity);
    btnRemove[i].addEventListener('click' , removeProduct);
}


const updateCart = () => {
    total.innerText = formatDollar(Number(dataObj.total))+" vnđ";
    discount.innerText = formatDollar(Number(dataObj.discount))+" vnđ";
    lastPrice.innerText = formatDollar(Number(dataObj.lastPrice))+" vnđ";
}
updateCart();
///////

var btnApply = document.getElementsByClassName('apply')[0];
const checkCoupon = () =>{
    let coupon = document.getElementsByClassName('inputStyle')[0].value;
    if (coupon ===""){
        return;
    }

}
btnApply.addEventListener('click',checkCoupon);