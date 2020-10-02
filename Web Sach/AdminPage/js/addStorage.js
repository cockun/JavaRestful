$(document).ready(function () {

  
    callApi("GET","admin/products").data.forEach(product => {
      $("#productCode").append(
        `<option value =${product.id}>${product.code+"-"+product.name}</option>`
      )
    })
 

    $("#btnAddStorage").click(function(){
        try{
           

            let tmp = {
                idProduct:$("#productCode").val(),
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
  