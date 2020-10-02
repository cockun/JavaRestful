$(document).ready(function () {
  callApi("GET", "admin/bills").data.forEach((bill) => {
    // console.log(tmp)

    $("#tbodybill").append(
      `
            <tr>
            <td style="width: 8%">
                ${bill.code}
            </td>
            <td style="width: 12%">
                ${bill.nameUser}
            </td>
            <td style="width: 10%">
                ${bill.date}
            </td>
            <td style="width: 9%">
                ${bill.codePromotion}
            </td>
            <td style="width:9%" >
                ${formatDollar(bill.discount)}
            </td>
            <td style="width: 7%" >
                ${formatDollar(bill.total)}
          </td>
  
            <td style="width: 10% " >
                <select class="selectOption" datar="${bill.id}" class="form-control form-control-lg " >
                    ${!bill.pay?"<option value = false selected  >Chưa thanh toán</option>  <option value = true >Đã thanh toán</option>":"<option value = false  >Chưa thanh toán</option><option selected  value = true  >Đã thanh toán</option>"}

                </select>
            </td> 
            
            <td class="project-actions "style="width: 16% ">
                 
                <a class="btn btn-info btn-sm" href="#">
                    <i class="fas fa-pencil-alt">
                    </i>
                    <button style="color: white;font-weight: bold" datar="${bill.id}"  type="button" class="btn btnView"  data-toggle="modal" data-target=".bd-example-modal-lg"  >Xem</button>
                </a>
                <a class="btn btn-info btn-sm" href="#">
                <i class="fas fa-trash">
                </i>
                <button style="color: white;font-weight: bold" type="button" class="btn btnDelete" datar=${bill.id} >Xóa</button>
            </a>
              
            </td>
        </tr>
        
            `
    );
  
  });


  $(document).on('click',".btnDelete",function(){
  

    swal({
      title: "Are you sure?",
      text: "Once deleted, you will not be able to recover this imaginary file!",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
          
    try{
     
      $.ajax({
        async: false,
        type: "DELETE",
      
        headers: { "Content-Type": "application/json" },
        url: "http://localhost:8080/admin/bills?id=" + $(this).attr("datar"),
        success: function (data) {
          swal({
            title: "Good job!",
            text: "You clicked the button!",
            icon: "success",
            button: "Aww yiss!",
          }).then(()=>{
            location.reload();
          });
        
         
        },
      });


    }catch{
      swal("Error");
    }
      } else {
        swal("Your imaginary file is safe!");
      }
    });
   


  
   
  })


  $(document).on("click", ".btnView", function () {
    $("#tbodybillinfo").html("");
    callApi("GET", "admin/bill", {
      id: $(this).attr("datar"),
    }).data.billInfoModel.forEach((billinfo) => {
      $("#tbodybillinfo").append(
        `
                <tr>
                    <th style="width: 8%">
                        ${billinfo.code}
                    </th>
                    <th style="width: 12%">
                        ${billinfo.nameProduct}
                    </th>
                    <th style="width: 10%">
                        ${billinfo.priceRoot}
                    </th>
                    <th style="width: 11%">
                        ${billinfo.discount}
                    </th>
                    <th style="width:9%" >
                        ${billinfo.quantity}
                    </th>
                    <th style="width: 10%" >
                        ${billinfo.total}
                    </th>
                
                </tr>
                `
      );
    });
  });


  $(document).on("change", ".selectOption", function () {
    console.log($(this).val())
      let tmp = {
        id:$(this).attr('datar'),
        pay:$(this).val()
      }
      tmp = JSON.stringify(tmp);
      try{
        let data=  callApi("PUT","admin/bill",tmp);
        console.log(data);
      }catch{
        alert("Error")
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
