var placeAdd = document.getElementsByClassName("placeAdd")[0];

$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/admin/accounts",

      success: function (datas) {
        sessionStorage.setItem("Accounts", JSON.stringify(datas.data));
        var x = 0;
        datas.data.forEach((data) => {
          x++;
          sessionStorage.setItem("values", x);
          $("#placeAdd").append(
            `<tr>
              <td style="width: auto" class="idAccount">
              ${x}
              </td>
              <td style="width: auto" class="idUser" >
                 ${data.user}
              </td>
              <td style="width: auto" class="text-center">
              ${data.name}
              </td> 
              <td style="width: auto"class="text-center">
              ${data.phone}
              </td>
              <td style="width: auto" class="text-center">
              ${data.address}
              </td> 
              
              <td class="project-state text-center overflowhide">
              ${data.email}
              </td>
              <td style="width: auto " class="text-left" >
                <select class="selectOptionAuthor" datar="${
                  data.id
                }" class="form-control form-control-lg " >
                    ${
                      !data.author
                        ? "<option value = false selected  >User</option>  <option value = true >Admin</option>"
                        : "<option value = false  >User</option><option selected  value = true  >Admin</option>"
                    }

                </select>
             </td> 
              <td class="project-actions text-right"style="width: auto ">
                 
                  <a class="btn btn-info btn-sm" href="EditAccount.html?id=${
                    data.id
                  }">
                      <i class="fas fa-pencil-alt">
                      </i>
                      <span >Edit</span>
                  </a>
                
                  <a class="btnDelete" href="#", data-item = "">
                              <i class="fas fa-trash" getID=${
                                data.id
                              } onclick="removeAccount(this)">
                              </i>
                           
                            
                          </a>
                          
              </td>
          </tr>
          `
          );
        });
      },
    });
  });

  $(document).on("change", ".selectOptionAuthor", function () {
 
      let tmp ={
        "author": $(this).val(),
        "id": $(this).attr("datar")
      }
      tmp = JSON.stringify(tmp);
      try{
        let data=  callApi("PUT","admin/account/author",tmp);
        console.log(data);
      }catch{
        alert("Error")
      }

  })









});


function removeAccount(event) {
  event = window.event;
  if (confirm("Bạn có chắc muốn xóa tài khoản  này không?")) {
    $.ajax({
      type: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      url: `http://localhost:8080/admin/account?id=${event.target.getAttribute(
        "getID"
      )}`,
      success: function (data) {
        alert("Success");
        console.log(data);
        // window.location="/Web%20Sach/AdminPage/Account.html";
      },
    });
  } else {
    return ;
  }
}


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

////////////////////////////
