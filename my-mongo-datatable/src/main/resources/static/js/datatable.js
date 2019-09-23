

var myTaskTable = "";

$(document).ready( function (){
	myTaskTable = "";
	myTaskTable = $('#taskTable')
	.DataTable(
		{
			"autoWidth" : false,
			"serverSide" : false,
			"stateSave" : true,
			"language" : {"emptyTable" : "No results found."},
			"deferRender" : true,
	        "ajax" : {
	        	 "url" : "/todoDataTable",
	             "type" : "GET"
	         },
	        "sDom": 'B<"H"lfr>t<"F"ip>',
	        //"buttons" : getButtonConfig([CSV_BUTTON]),
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"columns": [
			      { "data": "id", "visible" : false, "searchable" : true, "sortable" : true},
		          { "data": "taskName", "visible" : true, "searchable" : true, "sortable" : true, "width" : "20%"},
				  { "data": "owner", "visible" : true, "searchable" : true, "sortable" : true, "width" : "20%" },
				  { "data": "createDate", "visible" : true, "searchable" : true, "sortable" : true , "width" : "15%" },
				  { "data": "complete", "visible" : true, "searchable" : true, "sortable" : true, "width" : "10%"},
				  { "data": "completeDate", "visible" : true, "searchable" : true, "sortable" : true, "width" : "15%" },
				  { "title": "Actions", "data" : "null", "name" : "actions", "visible" : true, "searchable" : false, "sortable" : false, "width" : "15%",
					  "render" : function(data, type, full, meta) {
						  return "<div align='center'>" +
						  			"<span>" +
						  				"<button class='deleteButton'  id='delete" +full.Id+"' " +
						  						"value='"+meta.row+"'>Delete</button>" +
						  			"</span>" +
						  			"&nbsp;&nbsp;" +
						  			"<span>" +
						  				"<button class='updateButton'  id='update" +full.Id+"' " +
						  						"value='"+meta.row+"'>Update</button>" +
						  			"</span>" +
						  		   "</div>";
					  }
				  }
			]
	 })
	 console.log("myTaskTable: " + myTaskTable);
});

$(document).on('click', '.deleteButton', function(){
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myTaskTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log(rowData.id);
    
//    $('#taskTable').DataTable().clear();
//    $('#taskTable').DataTable().destroy();

    
    $.ajax({
        url: "todo/delete/" + rowData.id,
        data:rowData.id,
        type:"POST",
        success:function(response){
          console.log(response);
          if (response.successMessages){
        	  console.log("successMessages: " + response.successMessages);
        	  //myTaskTable.ajax.reload();
        	  console.log("ajax reload 1");
        	  
        	  //setResponseModalMessages("Success", response.successMessages);
          } else if(response.errorMessages) {
        	  console.log("errorMessages: " + response.errorMessages);
        	  //myTaskTable.ajax.reload();
        	  console.log("ajax reload 2");
            //setResponseModalMessages("Error", response.errorMessages);
          }
          myTaskTable.ajax.reload();
          console.log("ajax reload 3");
          
        },
        error:function(){
          console.log("error reciving data from backend");
          console.log("reload");
          //myTaskTable.ajax.reload();
          /*setResponseModalMessages("Failure", ["An error occured while deleting the user"]);*/
        }
      })
      
      //https://datatables.net/forums/discussion/7325/processing-notice-and-ajax-error-handling
      // think after adding the new user I might be receiving bad JSON>
      myTaskTable.ajax.reload();
});

$(document).on('click', '.updateButton', function(){
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myTaskTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log(rowData.id);
});

/*$(document).on('click', '.addButton', function(){
	console.log("addButton click");
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myTaskTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log(rowData.id);
});
*/	
	


