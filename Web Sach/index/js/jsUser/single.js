var btnBuy = document.getElementById('btnBuy');
var pdName = document.getElementsByClassName('pdName')[0].innerText;
var pdPrice = document.getElementsByClassName('pdPrice')[0].innerText;
var pdImg = document.getElementsByClassName('pdImg')[0].getAttribute('src');

const adđSessionProduct = () =>{
    if (sessionStorage.getItem("product") === null) {
      //  console.log(1);
        let pdObj = {
            'idUser': "Thiện Nghi",
            'length': 0,
            'total': 0,
            'discount': 0,
            'lastPrice':0,
            'product': []
        }
        sessionStorage.setItem("product",JSON.stringify(pdObj)); // khởi tạo
    }   


    let temp= JSON.parse(sessionStorage.getItem("product"));
    for (let i = 0 ; i < temp.product.length ; i++){
        if (pdName === temp.product[i].name){
            temp.length++;
            temp.product[i].quantity++;
            temp.total =Number(temp.total)+Number(temp.product[i].price);
            temp.lastPrice = Number(temp.total)+Number(temp.discount);
            sessionStorage.setItem("product",JSON.stringify(temp));
            return;
        }
    }
    let pdObj = {
        'name': pdName,
        'price': pdPrice,
        'img': pdImg,
        'quantity': 1,
    }
    temp.length++;
    temp.total = pdPrice*1;
    temp.lastPrice = Number(temp.total)+Number(temp.discount);
    temp.product.push(pdObj);
    sessionStorage.setItem("product",JSON.stringify(temp));
    return;
}

btnBuy.addEventListener('click',adđSessionProduct);

