$(document).ready(function () {
  callApi("GET", "admin/promotions").data.forEach((promotion) => {
    // console.log(tmp)

    $("#tbodyPromotion").append(
      `
      <tr>
                <td style="width: 8%" class="text-center">
                ${promotion.promotionCode}
                </td>
                <td style="width: 10% "  class="text-center">
                ${
                  !promotion.promotionCategory
                    ? "VNĐ"
                    : "%"
                }
                </td> 
                <td style="width: 10%" class="text-center">
                    ${formatDollar(promotion.discount) }
                </td>
    
            
              
              <td class="project-actions text-center "style="width: 16% ">
                   
                  <a class="btn btn-info btn-sm" href="#">
                      <i class="fas fa-pencil-alt">
                      </i>
                        <button type="button"  datar=${
                          promotion.id
                        } class="btn btnView" data-toggle="modal" data-target="#exampleModal">
                            Sửa
                        </button>
                  </a>
                  <a class="btn btn-info btn-sm" href="#">
                  <i class="fas fa-trash">
                  </i>
                  <button style="color: white;font-weight: bold" type="button" class="btn btnDelete" datar=${
                    promotion.id
                  } >Xóa</button>
              </a>
                
              </td>
          </tr>
          
              `
    );
  });

  $(document).on("click", ".btnDelete", function () {
    swal({
      title: "Are you sure?",
      text:
        "Once deleted, you will not be able to recover this imaginary file!",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        try {
          $.ajax({
            async: false,
            type: "DELETE",

            headers: { "Content-Type": "application/json" },
            url:
              "http://localhost:8080/admin/promotion?id=" + $(this).attr("datar"),
            success: function (data) {
              swal({
                title: "Good job!",
                text: "You clicked the button!",
                icon: "success",
                button: "Aww yiss!",
              }).then(() => {
                location.reload();
              });
            },
          });
        } catch {
          swal("Error");
        }
      } else {
        swal("Your imaginary file is safe!");
      }
    });
  });

  /// sửa

  $(document).on("click", ".btnView", function () {
    $(".modal-body").html("");
    let data = callApi("GET", "promotion?id=" + $(this).attr("datar"));
    $(".modal-body").append(
      `
            <div class="form-group">
                <label for="exampleInputEmail1">Mã Khuyến mãi</label>
                <input class="form-control form-control-lg promotionCode" value=${
                  data.data.promotionCode
                } type="text" placeholder="Mã Khuyến mãi">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail1">Loại giảm giá</label>
                <select style="" class="form-control form-control-lg promotionCategory">
                ${
                  !data.data.promotionCategory
                    ? "<option value = false selected  >VNĐ</option>  <option value = true >%</option>"
                    : "<option value = false  >VNĐ</option><option selected  value = true  >%</option>"
                }
              </select>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Giảm giá</label>
                <input class="form-control form-control-lg discount" datar=${data.data.id} value=${
                  data.data.discount
                } type="text" placeholder="Giảm giá" >
            </div>
           
            `
    );
  });


  $(document).on('click',".btnSavePromotion",function(){


        let obj = {
            promotionCode: $('.promotionCode').val(),
            promotionCategory:  $('.promotionCategory').val(),
            discount: $('.discount').val(),
            id: $('.discount').attr("datar")
        }
         obj = JSON.stringify(obj);
        try{
            callApi("PUT","admin/promotion",obj)
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
