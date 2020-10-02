
author = true;
$(document).ready(function () {
  document.getElementById('author1').onclick = function (e) {
    if (this.checked) {
      author = true;
      document.getElementById("author2").checked = false;
    }
  };
  document.getElementById('author2').onclick = async function (e) {
    if (this.checked) {
      author=false;
      document.getElementById("author1").checked = false;
    }
  };

});

function addAccount() {
  var name = document.getElementById("name").value;
  var user = document.getElementById("user").value;
  var password = document.getElementById("pass").value;
  var address = document.getElementById("address").value;
  var phone = document.getElementById("phone").value;
  var email = document.getElementById("email").value;

  var obj = {
      "id":'',
      "user": user,
      "password":password,
      "author":author,
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