
$(document).ready(function () {
	 var dataSource = shield.DataSource.create({
         data: "#dataTable",
         schema: {
             type: "table",
             fields: {
                 "Batch Name": { type: String },
                 "Teacher Name": { type: String },
                 "Total Income": { type: String },
                 "Teacher Fee" : {type : String},
                 "Supervisor Fee" : {type : String},
                 "Judge Fee" : {type : String},
                 "Other Expense" : {type : String},
                 Result : {type : String}
             }
         }
     });
    $("#btnPDF").click(function () {
        // parse the HTML table element having an id=exportTable
        // when parsing is done, export the data to PDF
        dataSource.read().then(function (data) {
            var pdf = new shield.exp.PDFDocument({
                author: "RegisteredUser",
                created: new Date(),
            
            });

            pdf.addPage("a4", "landscape");
            
         

            pdf.table(
                50,
                50,
                data,
                [  
                	
                    { field: "Batch Name", title: "Batch Name", width: 80 },
                    { field: "Teacher Name", title: "Teacher Name", width: 100 },
                    { field: "Total Income", title: "Total Income", width: 100 },  
                    { field: "Teacher Fee", title: "Teacher Fees", width: 100 },
                    { field: "Supervisor Fee", title: "Supervisor Fees", width: 100 },
                    { field: "Judge Fee", title: "Judge Fees", width: 100 },
                    { field: "Other Expense", title: "Other Expenses", width: 100 },
                    { field: "Result", title: "Result", width: 100 }
                ],
                {
                    margins: {
                        top: 50,
                        left: 40
                    }
                }
            );

            pdf.saveAs({
                fileName: "ProfitLoss"
            });
        });
    });
    
    
    $("#btnXLS").click(function () {
        // parse the HTML table element having an id=exportTable
       

        // when parsing is done, export the data to Excel
        dataSource.read().then(function (data) {
            new shield.exp.OOXMLWorkbook({
                author: "RegisteredUser",
                worksheets: [
                    {
                        name: "PrepBootstrap Table",
                        rows: [
                            {
                                cells: [
                                    {
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Batch Name"
                                    },
                                    {
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Teacher Name"
                                    },
                                    {
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Total Income"
                                    },{
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Teacher Fee"
                                    },{
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Supervisor Fee"
                                    },{
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Judge Fee"
                                    },{
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Other Expense"
                                    },{
                                        style: {
                                            bold: true
                                        },
                                        type: String,
                                        value: "Result"
                                    }
                                ]
                            }
                        ].concat($.map(data, function(item) {
                            return {
                                cells: [
                                    { type: String, value: item["Batch Name"]},
                                    { type: String, value: item["Teacher Name"]},
                                    { type: Double, value: item["Total Income"]},
                                    { type: Double, value: item["Teacher Fee"]},
                                    { type: Double, value: item["Supervisor Fee"]},
                                    { type: Double, value: item["Judge Fee"]},
                                    { type: Double, value: item["Other Expense"]},
                                    { type: Double, value: item.Result}
                                ]
                            };
                        }))
                    }
                ]
            }).saveAs({
                fileName: "ProfitLoss"
            });
        });
    });
});






