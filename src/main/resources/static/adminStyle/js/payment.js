$(document).ready(function () {
      $('input[type="checkbox"]').click(function () {
        var inputValue = $(this).attr("value");
        $("." + inputValue).toggle();
      });
      
     $("#searchButton").click(function() { 
    			var stCode = $("#searchStudentCode").val();
    				window.location.href = "/admin/studentPayment?stCode=" + stCode;
    			
    			
    }) 
    
  /*  function addPayment() {
		var stbid = $("#studentBatchId").val();
		if(stbid==""){
			window.location = "[[@{/admin/studentPayment}]]";
		}
		else {
			document.getElementById("studentBatchForm").action = "@{/admin/addPayment}";
			$("#studentBatchForm").submit();
		}
	}*/
     
     $("#submitBtn").click(function(){
    	 var stbid = $("#studentBatchId").val();
    	 if(stbid == ""){
    		 window.location.href = "/admin/studentPayment";
    	 }else{
    		 $("#studentBatchForm").submit();
    	 }
    	
     })
    
    var stb_Id=$("#stuBatch").val();
	     
        if (stb_Id ) {
          var btn_id = "#row-" + stb_Id;
          
          setTimeout(function() {
            $(btn_id).trigger("click");
          }, 10);
        }
        var searchStucode=$("#searchStudentCode").val();
        var serachKeyword=$("#searchKeyword").val();
        
    
     $("#dataTable tr").click(function() {
    	 $(this).addClass('selected').siblings().removeClass('selected'); 
	var stbId = $(this).find(".stbId").text();
	//alert(stbId);
	var searchStucode=$("#searchStudentCode").val();
	var actualAmount=$(this).find(".actualAmount").text();
	//alert(actualAmount);
	$("#studentBatchForm").trigger("reset");
	$("#inputAcutalAmount").val(actualAmount);
	$("#studentBatchId").val(stbId);
	$("#searchKeyword").val(searchStucode);
	$.get("/admin/getStudentPaymentListByStBatchId?stBatchId=" + stbId, function(data) {
		//alert(data.courseFee);
	})
		.done(function(data) {
			console.log(data);
			 $("#tableId").html("");
		     var tr="<tr><th>Paid Date</th><th>Installment</th><th>Actual Amount</th><th>Paid Amount</th><th>Remain Amount</th><th>Voucher Number</th></tr>";
			     for (var i = 0; i < data.length; i++){
			           tr+="<tr>";
			           tr+="<td>" + data[i].paidDate+ "</td>";
			           tr+="<td>" + data[i].installment + "</td>";
			           tr+="<td>" + data[i].actualAmount + "</td>";
			           tr+="<td>" + data[i].paidAmount + "</td>";
			           tr+="<td>" + data[i].remainAmount + "</td>";
			           tr+="<td>" + data[i].voucherNo + "</td>";
			           tr+="</tr>";
			     }
			     $("#tableId").append(tr);
			     
			    $("#paidAmount").val();
			     $("#paidDate").val();
			     $("#voucherNumber").val();
			     $("#errorMessage").empty();
			     
			     
		})
		.fail(function(data) {
			alert("error");
		})
		.always(function(data) {
			//alert("finished");
		}) 
	
	

})
  
    $('#paidAmount').on('change', function() {
		var paidamt = parseFloat($("#paidAmount").val());
		var actualamt =parseFloat( $("#inputAcutalAmount").val());
		if(actualamt < paidamt){
			$("#errorMessage").css("color", "red");
			 $("#errorMessage").html( "Paid Amount should be less than Actural Amount!!");
			 $("#paidAmount").val(null);
		} else{
			$("#errorMessage").empty();
		}
    })
    
    
    
    
    
    
    });




     
     
     
