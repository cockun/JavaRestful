$(document).ready(function () {
  $("#dateBegin").val("2020-11-01");
  $("#dateEnd").val("2020-12-31");
  let obj = {
    dateBegin: "2020-11-01",
    dateEnd: "2020-12-31",
  };
  searchProfit(obj);
});

$("#btnSearch").on("click", () => {
  $("#tbodyProfit").empty();
  $("#tfootProfit").empty();

  let dateBegin = $("#dateBegin").val();
  let dateEnd = $("#dateEnd").val();
  let obj = {
    dateBegin: dateBegin,
    dateEnd: dateEnd,
  };
  searchProfit(obj);
});

function searchProfit(obj) {
  let data = callApi("GET", "admin/Profit", obj).data;

  data.listProduct.forEach((p) => {
    $("#tbodyProfit").append(
      `
        <tr>
             
            <td style="width: 20% ; text-align: center">
                ${p.nameProduct}
            </td>
            <td style="width: 10% ; text-align: center">
                ${p.quantity}
            </td>
            <td style="width: 11% ; text-align: center">
                ${formatDollar(p.totalOut)}
            </td>
            <td style="width:9% ; text-align: center" >
                ${formatDollar(p.totalIn)}
            </td>
            <td style="width: 10% ; text-align: center" >
              ${formatDollar(p.totalWaiting)}
            </td>
            <td style="width: 10% ; text-align: center" >
                ${formatDollar(p.totalIn + p.totalWaiting - p.totalOut)}
            </td>



            <td class="project-actions "style="width: 16% ; text-align: right">
                 
            <a class="btn btn-info btn-sm" href="#">
                <i class="fas fa-pencil-alt">
                </i>
                <button style="color: white;font-weight: bold" datar="${
                  p.idProduct
                }"  type="button" class="btn btnView"  data-toggle="modal" data-target=".bd-example-modal-lg"  >Xem</button>
            </a>
           
            
            </td>
            
    
        </tr>
        `
    );
  });

  $("#tfootProfit").append(
    `
        <td style="width: 20% ; text-align: center">
                            
        </td>
      
         <td style="width: 10% ; text-align: center">
            <b> Tổng: </b>
        </td>
        <td style="width: 11% ; text-align: center">
            ${formatDollar(data.totalOut)}
        </td>
        <td style="width:9% ; text-align: center" >
            ${formatDollar(data.totalIn)}
        </td>
        <td style="width:9% ; text-align: center" >
        ${formatDollar(data.totalWaiting)}
    </td>
       
        <td style="width: 10% ; text-align: center" >
            ${formatDollar(data.totalIn +data.totalWaiting - data.totalOut)}
        </td>
        <td></td>
        `
  );
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

$(document).on("click", ".btnView", function () {
  let dateBegin = $("#dateBegin").val();
  let dateEnd = $("#dateEnd").val();
  $("#tbodybillinfo").html("");
  let obj = {
    idProduct: $(this).attr("datar"),
    dateBegin: dateBegin,
    dateEnd: dateEnd,
  };
  callApi("GET", "admin/ProfitInfo", obj).data.forEach((productProfit) => {
    $("#tbodybillinfo").append(
      `
                <tr>
                    <th style="width: 8%; text-align: center">
                        ${productProfit.name}
                    </th>
                    <th style="width: 12%; text-align: center">
                        ${formatDollar(productProfit.rootPrice)}
                    </th>
                    <th style="width: 10%; text-align: center">
                        ${formatDollar(productProfit.discount)}
                    </th>
                    <th style="width: 11%; text-align: center">
                        ${productProfit.quantity}
                    </th>
                    <th style="width:9%; text-align: center" >
                        ${formatDollar(productProfit.total)}
                    </th>
                    <th style="width: 10%; text-align: center" >
                        ${productProfit.userName}
                    </th>
                    <th style="width: 10%; text-align: center" >
                      ${productProfit.pay?"Đã thanh toán":"Chưa thanh toán" }
                    </th>
                    <th style="width: 10%; text-align: center" >
                        ${productProfit.date}
                    </th>
                
                </tr>
                `
    );
  });
});
