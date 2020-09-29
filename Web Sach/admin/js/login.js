// Login Form

$(function() {
    var button = $('#loginButton');
    var box = $('#loginBox');
    var form = $('#loginForm');
    button.removeAttr('href');
    button.mouseup(function(login) {
        box.toggle();
        button.toggleClass('active');
    });
    form.mouseup(function() { 
        return false;
    });
    $(this).mouseup(function(login) {
        if(!($(login.target).parent('#loginButton').length > 0)) {
            button.removeClass('active');
            box.hide();
        }
    });
});

var inputUserName = document.getElementById('modlgn_username');
var inputPassWord = document.getElementById('modlgn_passwd');

var formLogin = document.getElementById('login-form');

// function formLogin(){
//     var username = document.getElementById('inputUserName').value;
//     var password = document.getElementById('inputPassWord').value;
//     console.log(username);
//     console.log(password);
//     if ( username == "nguyen" && password == "123"){
//         alert ("Login successfully");
//     }
//     else{
//         alert("Invalid username or password");
//     }
// }

const formLoginz = (e) => {
    e.preventDefault();
    let name = document.getElementById("modlgn_username").value;
    let pass = document.getElementById("modlgn_passwd").value;
    if ( name == "nguyen" && pass == "123"){
        alert ("Login successfully");
    }
    else{
        alert("Invalid username or password");
    }
}
var button = document.getElementById("submit1");
button.addEventListener('click',formLoginz);



