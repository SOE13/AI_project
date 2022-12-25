 $(document).ready(function () {
      
        
        
        
        $('#teacherID').on('change', function() {
    		var teacherId = $("#teacherID").val();
    		//alert(teacherId);
    		if(teacherId==""){
    			$("#inputTeacherName").val(null);
    			
    		}else{
    			$.get("/admin/getTeacherName?teacherId=" + teacherId , function(data) {
        			//alert(data.courseFee);
        		})
        			.done(function(data) {
        				$("#inputTeacherName").val(data.name);
        				//inputBatchName = data.name;
        				//var batch=inputBatchName + inputBatchNumber;
        				// $('#batchName').val(batch);
        				
        			})
        			.fail(function(data) {
        				alert("error");
        			})
        			.always(function(data) {
        				//alert("finished");
        			});
    		}
    	});
        
       
        
        $('#inputBatchName').on('change', function() {
    		var courseId = $("#inputBatchName").val();
    		//alert(courseId);
    		if(courseId=="")
    			{
    			$("#inputBatchCourseFee").val(null);
    			}
    		else{
    			$.get("/admin/getCourseNameByID?courseId=" +courseId , function(data) {
        			console.log(data)
        			//alert(data.courseFee);
        		})
        			.done(function(data) {
        				$("#inputBatchCourseFee").val(data.fee);
        				$("#courseName").val(data.name);
        				//inputBatchName = data.name;
        				//var batch=inputBatchName + inputBatchNumber;
        				// $('#batchName').val(batch);
        				
        			})
        			.fail(function(data) {
        				console.log(data)
        				alert("error");
        			})
        			.always(function(data) {
        				//alert("finished");
        			});
    		}
    	
    		
    	});
        
        $('input[type="checkbox"]').click(function () {
            var inputValue = $(this).attr("value");
            $("." + inputValue).toggle();
          });
        
      });