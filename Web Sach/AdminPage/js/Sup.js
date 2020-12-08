var placeAdd = document.getElementsByClassName("placeAdd")[0];

$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/admin/supplier",

      success: function (datas) {
          console.log(datas)
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
              ${data.id}
              </td>
              <td class="project-actions text-right"style="width: auto ">
                 <div class="editdelte">
                 <a class="btn btn-info btn-sm" href="EditSup.html?id=${
                  data.id
                }">
                    <i class="fas fa-pencil-alt">
                    </i>
                    <span >Edit</span>
                </a>
              
                <a class="btnDelete" href="#", data-item = "">
                            <i class="fas fa-trash" getID=${
                              data.id
                            } onclick="removeSup(this)">
                            </i>
                         
                          
                        </a>
                 </div>
                  
                          
              </td>
          </tr>
          `
          );
        });
      },
    });
  });
//   $(document).on("change", ".selectOptionAuthor", function () {
 
//     let tmp ={
//       "author": $(this).val(),
//       "id": $(this).attr("datar")
//     }
//     tmp = JSON.stringify(tmp);
//     try{
//       let data=  callApi("PUT","admin/account/author",tmp);
//       console.log(data);
//     }catch{
//       alert("Error")
//     }

// })









});


//   $(document).on("change", ".selectOptionAuthor", function () {
 
//       let tmp ={
//         "author": $(this).val(),
//         "id": $(this).attr("datar")
//       }
//       tmp = JSON.stringify(tmp);
//       try{
//         let data=  callApi("PUT","admin/account/author",tmp);
//         console.log(data);
//       }catch{
//         alert("Error")
//       }

//   })









// });


function removeSup(event) {
  event = window.event;
  if (confirm("Bạn có chắc muốn xóa nhà cung cấp  này không?")) {
    $.ajax({
      type: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      url: `http://localhost:8080/admin/supplier?id=${event.target.getAttribute(
        "getID"
      )}`,
      success: function (data) {
        alert(data.message);
        console.log(data);
        location.reload();
      },
    });
  } else {
    return ;
  }
}


// function callApi(method, endpoint = "", data = null) {
//   var datar;
//   $.ajax({
//     async: false,
//     type: method,
//     data: data,
//     headers: { "Content-Type": "application/json" },
//     url: "http://localhost:8080/" + endpoint,
//     success: function (data) {
//       datar = data;
//     },
//   });
//   return datar;
// }

