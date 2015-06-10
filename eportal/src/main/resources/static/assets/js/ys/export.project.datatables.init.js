'use strict';

$(document).ready(function () {
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone : 480
    };
    var tableElement = $('#projectExportDT');



    var otable=tableElement.dataTable({
        "scrollY": "350px",
        "scrollX": "100%",
        "scrollCollapse": true,
        "paging": false,
        "dom": 'T<"clear">lfrtip',
         tableTools: {
             "sSwfPath": "assets/DataTables-1.10.7/extensions/TableTools/swf/copy_csv_xls_pdf.swf"
                },
        //"order": [[ 1, "desc" ]],
       //"autoWidth"        : true,
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
    //new $.fn.dataTable.FixedColumns( otable, {leftColumns: 2}  );
   });