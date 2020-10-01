
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
                 
                  <a class="btn btn-info btn-sm" href="">
                      <i class="fas fa-pencil-alt">
                      </i>
                      <span >Edit</span>
                  </a>
                
                  <a  class="btnDelete" href="#", data-item = "">
                              <i class="fas fa-trash" >
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


////////////////////////////
var a = parseInt(sessionStorage.getItem("values"));
var btnRemove   = document.getElementsByClassName('btnDelete');    
                   

const DeleteAcount =(e) =>
{
    // var x = document.getElementsByClassName("btnDelete").parentElement.nodeName;
    alert("coc");
}


for (let i = 0 ; i < a ; i++){
    btnRemove[i].addEventListener('click' , DeleteAcount);
}






