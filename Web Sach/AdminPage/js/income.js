$(document).ready(function () {
    console.log('dsdsdskj')
    callApi("GET", "admin/incomes").data.forEach((income) => {
      let bill = callApi("GET", "admin/bill?id="+income.idIncome,null).data
  
      $("#tbodyIncome").append(
        `
          <tr>
            <td style="width: 8%" class="text-left">
                ${income.code}
            </td>
            <td style="width: 12%" class="text-center">
                ${bill.code}
            </td>
            <td style="width: 10%" class="text-center">
                ${income.date}
            </td>
            <td style="width: 10%" class="text-center">
                ${income.cost}
            </td>
            <td style="width: 10%" class="text-center">
                ${income.note}
            </td>
            <td style="width: 10%" class="text-center">
                ${income.status?"Đã thanh toán":"Chưa thanh toán"}
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
  