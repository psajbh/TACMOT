var myUserTable = "";

$(document).ready( function (){
	console.log("executing ready function");
	myUserTable = $('#userTable')
	.DataTable(
		{
			"autoWidth" : false,
			"serverSide" : false,
			"stateSave" : true,
			"language" : {"emptyTable" : "No results found."},
			"deferRender" : true,
	        "ajax" : {
	        	 "url" : "/userDataTable",
	             "type" : "GET"
	         },
	        "sDom": 'B<"H"lfr>t<"F"ip>',
	        //"buttons" : getButtonConfig([CSV_BUTTON]),
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"columns": [
			      { "data": "id", "visible" : false, "searchable" : true, "sortable" : true},
		          { "data": "name", "visible" : true, "searchable" : true, "sortable" : true, "width" : "20%"},
				  { "data": "fullName", "visible" : true, "searchable" : true, "sortable" : true, "width" : "20%" },
//				  { "data": "lastName", "visible" : true, "searchable" : true, "sortable" : true , "width" : "15%" },
				  { "data": "phone", "visible" : true, "searchable" : true, "sortable" : true, "width" : "10%"},
				  { "data": "email", "visible" : true, "searchable" : true, "sortable" : true, "width" : "15%" },
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
	console.log("executing onclick deleteButton function");
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myUserTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log("delete todo start: " + rowData);
    
    $.ajax({
        url: "/user/delete/" + rowData.id,
        data:rowData.id,
        type:"POST",
        success:function(response){
          console.log(response);
          if (response.successMessages){
        	  console.log("successMessages: " + response.successMessages);
          } else if(response.errorMessages) {
        	  console.log("errorMessages: " + response.errorMessages);
          }
          myUserTable.ajax.reload();
          console.log("ajax reload 3");
          
        },
        error:function(){
          console.log("error receiving data from backend");
        }
      })
      
      //https://datatables.net/forums/discussion/7325/processing-notice-and-ajax-error-handling
      // think after adding the new user I might be receiving bad JSON>
      myUserTable.ajax.reload();
});




