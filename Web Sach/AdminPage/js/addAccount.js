function addAccount()
{
    var name = document.getElementById("name").value;
    var user = document.getElementById("user").value;
    var password = document.getElementById("pass").value;
    var address = document.getElementById("address").value;
    var phone = document.getElementById("phone").value; 
    var email = document.getElementById("email").value; 
    var author1 = document.getElementById("author1").value;
    if(author1.checked)
    {
        console.log("coc");
    }
    var obj = {
        "id":'',
        "user": user,
        "password":password,
        "author":true,
        "name":name,
        "phone":phone,
        "address":address,
        "email":email,

    };
      $.ajax({
        type: "POST",
        headers: { 
          
          'Content-Type': 'application/json' 
      },
        url: "http://localhost:8080/admin/Register",
        data : JSON.stringify(obj),
        success:function(data)
        {
            alert("Success");
            console.log(data);
        },
        
      })
    
       
}