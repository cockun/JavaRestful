function  callApi(method, endpoint ="",data = null){
    var datar ;
    $.ajax({    
        async :false,
        type: method,
        data : data,
        url:'http://localhost:8080/' + endpoint,
        success : function(data)
        {
           datar =  data;
        }
    })
    return datar;
}


function Register()
{
    var name = document.getElementById("Rgt_Name").value;
    var user = document.getElementById("Rgt_UserName").value;
    var password = document.getElementById("Rgt_Passwrd").value;
    var address = document.getElementById("Rgt_address").value;
    var phone = document.getElementById("Rgt_Num").value; 
    var obj = {
        "id":'1LirlpowUhzPPlGHZxMK',
        "user": user,
        "password":password,
        "author":true,
        "name":name,
        "phone":phone,
        "address":address
    };
      $.ajax({
        type: "POST",
        headers: { 
          
          'Content-Type': 'application/json' 
      },
        url: "http://localhost:8080/Register",
        data : JSON.stringify(obj),
        success:function(data)
        {
            alert("Success");
            console.log(data);
        },
        
      })
    
       
}
function foo() { 
  var name = document.getElementById("modlgn_username").value;
  var pass = document.getElementById("modlgn_passwd").value;

  var data = callApi("GET","login",{
    "user":name,
    "password":pass
  });
  if(data.message=="Success"){
    alert("đăng nhập thành công");
    sessionStorage.setItem("userInfo", JSON.stringify(data.data));
    
  }
  

 }
