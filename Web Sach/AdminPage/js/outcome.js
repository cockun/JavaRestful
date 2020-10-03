$(document).ready(function () {
    console.log('dsdsdskj')
    callApi("GET", "admin/outcomes").data.forEach((outcome) => {
      let product = callApi("GET", "admin/product?id="+outcome.idOutcome,null).data
  
      $("#tbodyIncome").append(
        `
          <tr>
            <td style="width: 8%" class="text-left">
                ${outcome.code}
            </td>
            <td style="width: 12%" class="text-center">
                ${product.code}
            </td>
            <td style="width: 10%" class="text-center">
                ${outcome.date}
            </td>

            <td style="width: 10%" class="text-center">
                ${outcome.quantity}
            </td>
           
            <td style="width: 10%" class="text-center">
                ${outcome.outcomeCategory}
            </td>
            <td style="width: 10%" class="text-center">
                ${outcome.cost}
            </td>
            
            <td style="width: 10%" class="text-center">
                ${outcome.note}
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
  