'use strict';

$(document).ready(function () {
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone : 480
    };
    var tableElement = $('#SampleDT');



    var otable=tableElement.dataTable({
        "order": [[ 2, "desc" ]],
        "columnDefs": [
                    {
                        'targets' : [ 0 ],
                        'sortable': false,
        			}],

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

$('#SampleDT').dataTable()
.columnFilter({aoColumns:[
                null,
    		    { sSelector: "#customerFilter",type:"text" },
				{ sSelector: "#salesOrderFilter",type:"text"},
				{ sSelector: "#bookDateFilter", type:"text"},
				{ sSelector: "#typeFilter", type:"select", values : ["VPM","PNA","CSN","WLAN","ASPRO"]   },
				{ sSelector: "#statusFilter", type:"select", values : ["Complete","Scheduled","R&D Support","In Process","Post Support","Booked","Proposed","undefined"]   },
				{ sSelector: "#healthFilter", type:"select", values : ["good", "warning", "at risk"]   }
				]});

});

/*
 $('#SampleDT').dataTable()
    		.columnFilter({sPlaceHolder: "head:before",aoColumns:[
                null,
    		    { type:"text" },
				{ type:"text"},
				{ type:"text"},
				{ type:"select", values : ["VPM","PNA","CSN","WLAN","ASPRO"]   },
				{ type:"select", values : ["Complete","Scheduled","R&D Support","In Process","Post Support","Booked","Proposed","undefined"]   },
				{ type:"select", values : ["good", "warning", "at risk"]   }
				]}
			);
*/
/*
$('#SampleDT').dataTable()
			.columnFilter({aoColumns:[
				{ sSelector: "#renderingEngineFilter", type:"select"  },
				{ sSelector: "#browserFilter" },
				{ sSelector: "#platformsFilter" },
				{ sSelector: "#engineVersionFilter", type:"number-range" },
				{ sSelector: "#cssGradeFilter", type:"select", values : ["A", "B", "C"] }
				]}
			);
*/

 //<p id="customerFilter"></p>
   // <p id="salesOrderFilter"></p>
   // <p id="bookDateFilter"></p>
   // <p id="typeFilter"></p>
   // <p id="statusFilter"></p>
   // <p id="healthFilter"></p>
/*
   $('#SampleDT').dataTable()
    		.columnFilter({aoColumns:[
                null,
    		    { sSelector: "#customerFilter" },
				{ sSelector: "#salesOrderFilter"},
				{ sSelector: "#bookDateFilter", type:"date-range"},
				{ sSelector: "#typeFilter", type:"select", values : ["", "", ""]   },
				{ sSelector: "#statusFilter", type:"select", values : ["undefined","Complete","Scheduled","R&D Support","In Process","Post Support","Booked","Proposed"]   },
				{ sSelector: "#healthFilter", type:"select", values : ["good", "warning", "at risk"]   }
				]}
			);

*/
