 $(document).ready(function () {
    	
    	 var dataSource = shield.DataSource.create({
             data: "#dataTable",
             schema: {
                 type: "table",
                 fields: {
                     Name: { type: String },
                     Email: { type: String },
                     Phone: { type: String },
                     "Join Course" : {type : String},                     
                     Date : {type : String},
                     "Known From" : {type : String},
                     Address : {type : String}
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
                        { field: "Name", title: "Name", width: 100 },
                        { field: "Email", title: "Email", width: 100 },
                        { field: "Phone", title: "Phone Number", width: 100 },  
                        { field: "Join Course", title: "Join Course", width: 100 },                      
                        { field: "Date", title: "Date", width: 100 },
                        { field: "Known From", title: "Known From", width: 100 },
                        { field: "Address", title: "Address", width: 100 }
                    ],
                    {
                        margins: {
                            top: 50,
                            left: 50
                        }
                    }
                );

                pdf.saveAs({
                    fileName: "EnquiryUser"
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
                                            value: "Name"
                                        },
                                        {
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Email"
                                        },
                                        {
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Phone"
                                        },{
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Join Course"
                                        },{
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Date"
                                        },{
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Known From"
                                        },
                                        {
                                            style: {
                                                bold: true
                                            },
                                            type: String,
                                            value: "Date"
                                        }
                                    ]
                                }
                            ].concat($.map(data, function(item) {
                                return {
                                    cells: [
                                        { type: String, value: item.Name },
                                        { type: String, value: item.Email },
                                        { type: String, value: item.Phone },
                                        { type: String, value: item["Join Course"]},
                                        { type: String, value: item.Date },
                                        { type: String, value: item["Known From"]},
                                        { type: String, value: item.Address}
                                    ]
                                };
                            }))
                        }
                    ]
                }).saveAs({
                    fileName: "EnquiryUser"
                });
            });
        });
    });

