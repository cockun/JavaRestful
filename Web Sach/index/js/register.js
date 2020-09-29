const Register = (e) => {
    //e.preventDefault();
    var Name = document.getElementById('Rgt_Name').value;
    var UserName1 = document.getElementById('Rgt_UserName').value;
    var Email = document.getElementById('Rgt_Mail').value;
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var Password = document.getElementById('Rgt_Passwrd').value;
    var Address = document.getElementById('Rgt_Address').value;
    var Country = document.getElementById('country').value;
    var NumberPhone = document.getElementById('Rgt_Num').value;
    console.log(Email);
    if(Name== "") {
        alert("Please enter your Name");
        return false;
    }
    if(UserName1== "") {
        alert("Please enter your Username");
        return false;
    }
    if(!filter.test(Email)) {
        alert("Please enter your Email");
        Email.focus;
        return false;
    }
    if(Password== "") {
        alert("Please enter your Password, 8 character");
        return false;
    }
    if(Address== "") {
        alert("Please enter your Address");
        return false;
    }
    if(Country== "") {
        alert("Please enter your Country");
        return false;
    }
    if(NumberPhone== "") {
        alert("Please enter your SDT, 10 character");
        return false;
    }
    alert("Bạn đã đăng ký thành công");
    return true;
}
var button = document.getElementById("sb");
button.addEventListener('click',Register);