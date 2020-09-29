var placeAdd = document.getElementsByClassName("placeAdd")[0];
$(document).ready(function () {
  $(function () {
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/products",
      success: function (datas) {
        datas.data.forEach(data => {
            console.log(data);
            $("#placeAdd").append(
                `<div class="productD">
                <a href="single.html">
                <div class="inner_content clearfix">
                <div class="product_image">
                    <img src="${data.pic}" alt=""/>
                </div>
                
                <div class="price">
                   <div class="cart-left">  
                        <p class="title">${data.name}</p>
                        <div class="price1">
                          <span class="actual">${data.discount}</span>
                        </div>
                    </div>
                    <div class="cart-right"> </div>
                    <div class="clear"></div>
                 </div>
               </div>
               </a>
            </div>`
            )
           
        });
      },
    });
  });
});
