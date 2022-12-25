 $(document).ready(function () {
	 var inputBatchName='';
	    var inputBatchNumber='';
	 $('#inputBatchName').on('change', function() {
         inputBatchName=this.value
 console.log(this.value)
});
 	

$('#check-batch').click(function(e){
	  e.preventDefault();
	 inputBatchNumber = $('#inputBatchNumber').val();
	  var batchName=inputBatchName +"-"+ inputBatchNumber;
	  console.log(batchName)
		window.location.href = "/admin/getBatch?batchName=" + batchName; 
  })
 });