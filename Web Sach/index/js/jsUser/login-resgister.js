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
function foo() {
    
   var name = document.getElementById("modlgn_username").value;
   var pass = document.getElementById("modlgn_passwd").value;  
       if(callApi("GET","login",{
            "user":name,
            "passwrod":pass
       })==null )
       {
         alert("ngu");
         
       }
   
  
}

function Register()
{
    var name = document.getElementById("Rgt_Name").value;
    var user = document.getElementById("Rgt_UserName").value;
    var password = document.getElementById("Rgt_address").value;
    var address = document.getElementById("Rgt_Passwrd").value;
    var phone = document.getElementById("Rgt_Num").value; 
    var obj = {
        "id":'',
        "user": "user",
        "password":"password",
        "author":false,
        "name":"name",
        "phone":"phone",
        "address":"address"
    };
    console.log(obj);
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/Register",
        data : JSON.stringify(obj),
        success:function()
        {
            alert("dc bạn ")
        },
        error : alert("ngu")
      })
    
       
}