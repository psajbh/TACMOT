

var myTaskTable = "";

$(document).ready( function (){
	$('updateRow').hide();
	$('indexRow').show();
	
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
    
    $.ajax({
        url: "/todo/delete/" + rowData.id,
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
	console.log("building update html");
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myTaskTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log('id: '+rowData.id+' taskName: '+rowData.taskName+' owner: '+rowData.owner+' complete: '+rowData.complete);
    
	$('#indexRow').hide();
	$('#updateRow').show();
    
    if (rowData.complete == 'yes'){
    	alert("Cannot update a completed task");
    	return;
    }
   
	var updateForm = "<form id='updateFormDiv'>"+
						"<input id='updateId' type='hidden' class='form-control' name='id' value='"+rowData.id+"'/>"+
					 	"<div class='row'>" +
					 		"<div class='col-md-6'><label for='updateTaskNameId'>Task Name: </label><input id='updateTaskNameId' type='text' class='form-control' name='taskName' value='"+rowData.taskName+"'/></div>"+
					 		"<div class='col-md-4'><label for='updateOwnerId'>Task Owner: </label><input id='updateOwnerId' type='text' class='form-control' name='owner' value='"+rowData.owner+"'/></div>"+
					 		"<div class='col-md-2'><label for='completeStatusId'>Completed:</label>" +
					 		"<select id='completeStatusId' class='form-control' style='width : 100px;'>"+
					 			"<option value='Yes'>Yes</option>" +
					 			"<option selected value='No'>No</option>" +
					 		"</select></div>" +
					 	"</div><br/>"+
					 	"<div class='row'>"+
					 		"<div class='col-md-12 col-centered'>"+
					 			"<span><button type='submit' id='todoUpdate' class='executeUpdateBtn'>Update Task</button>" +
					 			"&nbsp;&nbsp;" +
					 			"<button class='executeUpdateCancelBtn'>Cancel</button></span>"+
					 		"</div>"+
					 	"</div>"+
					 	"</form>";
					 	
	$('#updateFormDiv').html(updateForm);
	console.log("finished building update html");
	
});


$(document).on('click', '#todoUpdate', function(event){
	console.log("executing update function");
	var todoBackBean = {};
	todoBackBean["id"] = $('#updateId').val();
	todoBackBean["taskName"] = $('#updateTaskNameId').val();
	todoBackBean["owner"] = $('#updateOwnerId').val();
	todoBackBean["complete"] = $('#completeStatusId').val();
	
	$.ajax({
		contentType:"application/json",
		url: "/todo/update",
		data:JSON.stringify(todoBackBean),
		type:"POST",
		dataType:"json",
		cache:false,
		success:function(response){
			//console.log(response);
			if (response.successMessages){
				console.log("successMessages: " + response.successMessages);
				//myTaskTable.ajax.reload();
				//console.log("ajax reload 1");
				setResponseModalMessages("Success", response.successMessages);
			} 
			else if(response.errorMessages) {
				console.log("errorMessages: " + response.errorMessages);
				//myTaskTable.ajax.reload();
				console.log("ajax reload 2");
				setResponseModalMessages("Error", response.errorMessages);
			}
		},
		error:function(e){
			console.log("error reciving data from backend: " + e);
		}
	});
	console.log("finished executing update function");
});


	
	




$(document).on('click', '.executeUpdateCancelBtn', function(event){
	event.preventDefault();
	$('#indexRow').show();
	$('#updateRow').hide();

});

$(document).on('click', '.cancelAddlButton', function(event){
	event.preventDefault();
	$('#indexRow').show();
	$('#updateRow').hide();

});	



