var rateData = JSON.parse(sessionStorage.getItem('rateObj'));
document.getElementsByClassName('namePd')[0].value = rateData.nameProduct;
document.getElementsByClassName('billCode')[0].value=rateData.billCode;
document.getElementsByClassName('name')[0].value = rateData.userName;
var point = 0;

const getReward = () => {
    let getStar = document.getElementsByClassName('star');
    for (let i = 0 ; i < getStar.length ; i++){
        if (getStar[i].checked === true){
            point = parseInt(getStar[i].value);
        }
    }
}

const submitRate = (e) => {
    let submitObj = {
        comment: document.getElementsByClassName('message')[0].value,
        idBill: rateData.billCode,
        idProduct: rateData.idProduct,
        idUser: rateData.idAcc,
        reviewPoint: parseInt(point)
    }
    console.log(submitObj);
    $.ajax({
        type:"POST",
        headers: { 
          
            'Content-Type': 'application/json' 
        },
        url: "http://localhost:8080/review",
        data:JSON.stringify(submitObj),
        success:function(data)
        {
          if (data.success){
            alert(data.message);
            sessionStorage.removeItem("rateObj");
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
}
document.getElementsByClassName('submit')[0].addEventListener('click' , submitRate)