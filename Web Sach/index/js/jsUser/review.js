var rateData = JSON.parse(sessionStorage.getItem('rateObj'));
document.getElementsByClassName('namePd')[0].value = rateData.nameProduct;
document.getElementsByClassName('billCode')[0].value=rateData.billCode;
document.getElementsByClassName('name')[0].value = rateData.userName;

const submitRate = (e) => {
    let submitObj = {
        comment: document.getElementsByClassName('message')[0].value,
        idBill: rateData.billCode,
        idProduct: rateData.idProduct,
        idUser: rateData.userName,
        reviewPoint: parseFloat(document.getElementsByClassName('rate')[0].value)
    }
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