$(document).ready(function () {
  callApi("GET", "admin/storage").data.forEach((storage) => {
    let product = callApi("GET", "admin/product?id="+storage.idProduct,null).data

    $("#tbodyPromotion").append(
      `
        <tr>
          <td style="width: 8%" class="text-left">
              ${storage.code}
          </td>
          <td style="width: 12%" class="text-center">
             ${storage.date}
          </td>
          <td style="width: 10%" class="text-center">
          ${product.code}
          </td>
          <td style="width: 10%" class="text-center">
          ${product.name}
          </td>
          <td style="width: 10%" class="text-center">
          ${formatDollar(product.rootprice) }
          </td>
          <td style="width: 10%" class="text-center">
          ${storage.quantity}
          </td>
        
    </tr>
              `
    );
  });

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
