 

  $(document).ready(function () {
  
	  
	  
	  
  $("#searchButton").click(function() {
	 
		var stCode = $("#searchStudentCode").val();
		window.location.href = "/admin/studentBatchRegister?stCode=" + stCode;
	})
	var st_Id=$("#stNewId").val();
	   
       if (st_Id ) {
         var btn_id = "#row-" + st_Id;
         
         setTimeout(function() {
           $(btn_id).trigger("click");
         }, 10);
       }
	 $("#dataTable tr").click(function() {
	 $(this).addClass('selected').siblings().removeClass('selected'); 
	var stId = $(this).find(".stId").text();
	var stName = $(this).find(".stName").text();
	var stSearch=$("#searchStudentCode").val();
	$("#studentBatchForm").trigger("reset");
	$("#studentId").val(stId);
	$("#studentName").val(stName);
	$("#stucode").val(stSearch);
})

$("#submitBtn").click(function(){
    	 var stid = $("#studentId").val();
    	 if(stid == ""){
    		 window.location.href = "/admin/studentBatchRegister";
    	 }else{
    		 $("#studentBatchForm").submit();
    	 }
    	
     })
 



 		var inputBatchName='';
    	var inputBatchNumber='';
    	var realBatchName="";
      /* $('#inputBatchName').on('change', function() {
            inputBatchName=this.value
   			 console.log(this.value)
  });*/
 $('#inputBatchName').on('change', function() {
		var courseId = $("#inputBatchName").val();
		if(courseId == ""){
			$("#inputBatchName").val(null);
			 $('#batchNo').val(null);
			 $("#courseFee").val(null);
		} 
		else{
		//alert("ZZZz");
		$.get("/admin/getCourseType?courseId=" + courseId , function(data) {
			//alert(data.courseFee);
		})
			.done(function(data) {
				inputBatchName = data.name;	
				 $('#batchNo').val(null);
			})
			.fail(function(data) {
				alert("error");
			})
			.always(function(data) {
				//alert("finished");
			})
		}
  })
 
 $('#batchNo' ).on('change', function() {
	 
	  inputBatchNumber =   $('#batchNo').val();
	  if(inputBatchNumber==0){
		  $("#errorMessage").empty();
	  }else{
    var batchName=inputBatchName + "-" + inputBatchNumber;
  
    $.get("/admin/getBatchInfo?batchName=" + batchName, function(data) {
		//alert("zz");
	})
	.done(function(data) {
		console.log("data =" +data);
		if (!jQuery.isEmptyObject(data))
	    {
			//alert("have data ")
			$("#BatchId").val(data.id)
			$("#courseFee").val(data.course.fee.toFixed(2));
			$("#errorMessage").empty();
		}else{
			$("#courseFee").val(null);
			$("#errorMessage").css("color", "red");
			 $("#errorMessage").html( batchName + " is not register yet!!");
			 $("#bindErr").empty();
			
			//alert(batchName + " is not register" );
		}
		
		
		//realBatchName=data.batchName;
			//courseFee=data.courseFee;
			
		})
		.fail(function(data) {
			alert("error");
		})
		.always(function(data) {
			//alert("finished");
		})
	  }

});
    
  $("#discountType").on('change', function() {
		var disTypeId = $("#discountType").val();
		
			if(disTypeId == ""){
				var courseFee = $("#courseFee").val();
				var discountPercent = 0;
				//var discountAmount = courseFee * (discountPercent / 100);
				$("#discountPercent").val(0+"%");
				$("#discountAmount").val("0");
				$("#actualAmount").val(courseFee);
			} else{
				
				$.get("/admin/getDiscountType?discountTypeId=" + disTypeId, function(data) {
					//alert(data.courseFee);
				})
					.done(function(data) {
						var courseFee = $("#courseFee").val();
						var discountPercent = data.discountPercent;
						var discountAmount = courseFee * (discountPercent / 100);
						var actualAmount = courseFee - discountAmount;

						$("#discountPercent").val(discountPercent+"%");
						$("#discountAmount").val(discountAmount.toFixed(2));
						$("#actualAmount").val(actualAmount.toFixed(2));

					})
					.fail(function(data) {
						alert("error");
					})
					.always(function(data) {
						//alert("finished");
					})
				
			}
		

	  })
	  
	  $('#checkBox').click(function(){
		    //If the checkbox is checked.
		    if($(this).is(':checked')){
		        //Disable the submit button.
		        //$('#courseFee').attr("disabled", true).val();
		        $('#discountType').attr("disabled", true).val(null);
		        $('#discountAmount').attr("disabled", true).val(null);
		        $('#actualAmount').attr("disabled", true).val(null);
		        
		    } else{
		        //If it is not checked, enable the button.
		    	//$('#courseFee').attr("disabled", false);
		        $('#discountType').attr("disabled", false).val(null);
		        $('#discountAmount').attr("disabled", false).val(null);
		        $('#actualAmount').attr("disabled", false).val(null);
		       // $('#batchNo').val(null);
		       // $("#inputBatchName").val(null);
		    }
		});
   
 });  
 

/*$('#realBatchName').on('change', function() {
	var batchId = $("#realBatchName").val();
	$.get("/admin/getBatchInfo?batchId=" + batchId, function(data) {
		//alert(data.courseFee);
	})
		.done(function(data) {
			$("#courseFee").val(data.courseFee.toFixed(2));
		/*	realBatchName=data.batchName;
			courseFee=data.courseFee;
			
		})
		.fail(function(data) {
			alert("error");
		})
		.always(function(data) {
			//alert("finished");
		})

})*/


  