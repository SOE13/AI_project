
$(document).ready(function() {	
	$("#btnSearch").click(function() {
		alert("search")
		var p1 = $("#p1").val();
		if(p1==""){
			document.getElementById("error").innerHTML="The field can not be blank!";
		}else{
			window.location.href = "/admin/searchStudent?p1=" + p1;	
		}
				

	})

});
