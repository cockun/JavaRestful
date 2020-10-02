$(document).ready(function () {

    $("#btnAddStorage").click(function(){
        try{
            let idProduct = callApi("GET","product/code?value="+ $("#productCode").val()).data.id;

            let tmp = {
                idProduct:idProduct,
                quantity:$("#quantity").val(),
                note:$("#note").val()
            }
            tmp = JSON.stringify(tmp)
            callApi("POST","admin/storage",tmp)
            swal({
                title: "Good job!",
                text: "You clicked the button!",
                icon: "success",
                button: "Aww yiss!",
              }).then(() => {
                location.reload();
              });
        }catch{
            swal("Error");
        }
   


    })
  
});
  
  function callApi(method, endpoint = "", data = null) {
    var datar;
    $.ajax({
      async: false,
      type: method,
      data: data,
      headers: { "Content-Type": "application/json" },
      url: "http://localhost:8080/" + endpoint,
      success: function (data) {
        datar = data;
      },
    });
    return datar;
  }
  