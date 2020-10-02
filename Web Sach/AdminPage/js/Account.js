
var placeAdd = document.getElementsByClassName("placeAdd")[0];

$(document).ready(function () {
    
    $(function () {
    
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/admin/accounts",    

        success: function (datas) { 
            sessionStorage.setItem("Accounts",JSON.stringify(datas.data));
            var x = 0;
          datas.data.forEach((data) => {
            x++;
            sessionStorage.setItem("values",x);
            $("#placeAdd").append(
          
              `<tr>
              <td style="width: 1%" class="idAccount">
              ${x}
              </td>
              <td style="width: 20%" class="idUser" >
                 ${data.user}
              </td>
              <td style="width: 10%" class="text-center">
              ${data.name}
              </td> 
              <td style="width: 10%"class="text-center">
              ${data.phone}
              </td>
              <td style="width: 10%" class="text-center">
              ${data.address}
              </td> 
              <td class="project-state text-left overflowhide">
              ${data.email}
              </td>
              <td class="project-actions text-right"style="width: 15% ">
                 
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
  });
  function removeAccount(event) {
    event=window.event;
    if (confirm("Bạn có chắc muốn xóa tài khoản  này không?")) {
        $.ajax({
          type:"DELETE",
          headers: { 
              'Content-Type': 'application/json' 
          },
          url: `http://localhost:8080/admin/account?id=${event.target.getAttribute("getID")}`,
          success:function(data)
          {
              alert("Success");
              console.log(data);
              // window.location="/Web%20Sach/AdminPage/Account.html";
          },
  
      })
    } else {
      return;
    }
  }
  

  


////////////////////////////










