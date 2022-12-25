$(document).ready(function() {
	var hobbies = $("#days").val().split(",");
	var $checkboxes = $("input[type=checkbox]");
	$checkboxes.each(function(idx, element) {
		if (hobbies.indexOf(element.value) != -1) {
			element.setAttribute("checked", "checked");
			$("#days").val("");
		} else {
			element.removeAttribute("checked");
		}
	});
});
