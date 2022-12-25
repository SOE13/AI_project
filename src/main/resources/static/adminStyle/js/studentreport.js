 $(document).ready(function () {

    	 var dataSource = shield.DataSource.create({
             data: "#dataTable",
             schema: {
                 type: "table",
                 fields: {
                     "Student ID": { type: String },
                     "Student Name": { type: String },
                     "Batch Name": { type: String },
                     Grade : {type : String}
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

                pdf.addPage("a4", "portrait");
                
             

                pdf.table(
                    50,
                    50,
                    data,
                    [                        
                        { field: "Student ID", title: "Student ID", width: 100 },
                        { field: "Student Name", title: "Student Name", width: 100 },
                        { field: "Batch Name", title: "Batch Name", width: 100 },  
                        { field: "Grade", title: "Grade", width: 100 }
                    ],
                    {
                        margins: {
                            top: 50,
                            left: 50
                        }
                    }
                );

                pdf.saveAs({
                    fileName: "StudentReport"
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
                                            value: "Student ID"
                                        },
                                        {
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Student Name"
                                        },
                                        {
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Batch Name"
                                        },{
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Grade"
                                        }
                                    ]
                                }
                            ].concat($.map(data, function(item) {
                                return {
                                    cells: [
                                        { type: String, value: item["Student ID"]},
                                        { type: String, value: item["Student Name"]},
                                        { type: String, value: item["Batch Name"] },
                                        { type: String, value: item.Grade}
                                    ]
                                };
                            }))
                        }
                    ]
                }).saveAs({
                    fileName: "StudentReport"
                });
            });
        });
    });

