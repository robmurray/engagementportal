'use strict';

$(document).ready(function () {
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone : 480
    };
    var tableElement = $('#projectExportDT');



    var otable=tableElement.dataTable({
           "scrollX": true,
        "dom": 'T<"clear">lfrtip',
         tableTools: {
             "sSwfPath": "assets/DataTables-1.10.7/extensions/TableTools/swf/copy_csv_xls_pdf.swf"
                },
        "order": [[ 1, "desc" ]],
        /*"columnDefs": [
                    {
                        'targets' : [ 0 ],
                        'sortable': false,
        			}],
*/
       "autoWidth"        : false,
       "aLengthMenu": [[-1,5, 10, 15, 25, 50, 100], ["all",5, 10, 15, 25, 50, 100]],
        "iDisplayLength" : -1,
        preDrawCallback: function () {
            // Initialize the responsive datatables helper once.
            if (!responsiveHelper) {
               responsiveHelper = new ResponsiveDatatablesHelper(tableElement, breakpointDefinition);
           }
        },
        rowCallback    : function (nRow) {
            responsiveHelper.createExpandIcon(nRow);
        },
        drawCallback   : function (oSettings) {
            responsiveHelper.respond();
        }
    });
/*
$('#projectExportDT').dataTable()
   .columnFilter({aoColumns:[
                   null,
       		    { sSelector: "#customerFilter",type:"text" },
   				{ sSelector: "#salesOrderFilter",type:"text"},
   				{ sSelector: "#bookDateFilter", type:"text"},
   				{ sSelector: "#typeFilter", type:"select", values : ["VPM","PNA","CSN","WLAN","ASPRO"]   },
   				{ sSelector: "#statusFilter", type:"select", values : ["Complete","Scheduled","R and D Support","In Process","Post Support","Booked","Proposed","Imported"]   },
   				{ sSelector: "#healthFilter", type:"select", values : ["good", "warning", "at risk"]   }
   				]});
*/
   });