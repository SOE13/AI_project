 $(document).ready(function () {



$('#batchName').on('change', function() {
var batchName = $("#batchName").val();

if(batchName==""){
$("#tableId").html("");
var tr="<tr><th>Paid Date</th><th>Installment</th><th>Actual Amount</th><th>Paid Amount</th><th>Remain Amount</th><th>Voucher Number</th></tr>";
$("#tableId").append(tr);
}else{
$.get("/studentProfile/getBatchName?batchName=" + batchName , function(data) {
})
.done(function(data) {
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

})
.fail(function(data) {
alert("error");
})
.always(function(data) {
//alert("finished");
});
}
});

});