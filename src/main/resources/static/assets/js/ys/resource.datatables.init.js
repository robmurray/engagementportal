'use strict';

$(document).ready(function () {
    var responsiveHelper = undefined;
    var breakpointDefinition = {
        tablet: 1024,
        phone : 480
    };
    var tableElement = $('#resourceDT');



    var otable=tableElement.dataTable({
        "order": [[ 1, "desc" ]],
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

    $('#resourceDT').dataTable()
    .columnFilter({aoColumns:[
                null,
    		    { sSelector: "#firstNameFilter",type:"text" },
				{ sSelector: "#lastNameFilter",type:"text"},
				{ sSelector: "#typeFilter", type:"select", values : ["Account Team","Trainer","Technical","Sales","Project Manager"]   }
	]});

});
