

var myTaskTable = "";

$(document).ready( function (){
	console.log("executing ready function");
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
				  { "data": "user.name", "visible" : true, "searchable" : true, "sortable" : true, "width" : "20%" },
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
	console.log("executing onclick deleteButton function");
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myTaskTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log("delete todo start: " + rowData);
    
    $.ajax({
        url: "/todo/delete/" + rowData.id,
        data:rowData.id,
        type:"POST",
        success:function(response){
          console.log(response);
          if (response.successMessages){
        	  console.log("successMessages: " + response.successMessages);
          } else if(response.errorMessages) {
        	  console.log("errorMessages: " + response.errorMessages);
          }
          myTaskTable.ajax.reload();
          console.log("ajax reload 3");
          
        },
        error:function(){
          console.log("error receiving data from backend");
        }
      })
      
      //https://datatables.net/forums/discussion/7325/processing-notice-and-ajax-error-handling
      // think after adding the new user I might be receiving bad JSON>
      myTaskTable.ajax.reload();
});


var ownerList = "";

$(document).on('click', '.updateButton', function(){
	console.log("executing onclick todo updateButton function");
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myTaskTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log('task values - id: '+rowData.id+' taskName: '+rowData.taskName+' owner: '+rowData.user.name+' complete: '+rowData.complete);
    
    var currentOwner = rowData.user.name;
    console.log("current owner: " + currentOwner);
    		
    $.post({
    	url : "../todo/users",
    	contentType : 'text/plain',    	
    	dataType : 'text',
    	data : currentOwner,
    	success : function(ownerOptions){
    		console.log("success: " + ownerOptions);
    		$('#owners').append(ownerOptions);
    	},
    	error : function(xhr, status, error){
    		console.log("failure:  xhr: " + xhr.responseText + "status: " + status + "error: " + error);
    		alert("failure todo/users post: " + xhr.responseText + "status: " + status + "error: " + error);
    	}
    });
    

    console.log("owner options completed");
    
    
    
	$('#indexRow').hide();
	$('#updateRow').show();
	
	var selectOption;

	if (rowData.complete == 'Yes'){
		selectOption = "<option selected value='Yes'>Yes</option><option value='No'>No</option>";
	}
	else{
		selectOption = "<option value='Yes'>Yes</option><option selected value='No'>No</option>";
	}

	console.log("selectOption: " + selectOption);
    
	var updateForm = 
		"<input id='updateId' type='hidden' class='form-control' name='id' value='"+rowData.id+"'/>"+
		"<div class='row'>" +
			"<div class='col-md-6'>"+
				"<label for='updateTaskNameId'>Task Name: </label>"+
				"<input id='updateTaskNameId' type='text' class='form-control' name='taskName' "+
				"value='"+rowData.taskName+"'/>"+
			"</div>"+
			"<div class='col-md-4'>" +
				"<label for='owners'>Task Owner: </label>" +
				"<select id='owners' class='form-control' style='width : 300px;'></select>" +
			"</div>"+
			"<div class='col-md-2'>"+
				"<label for='completeStatusId'>Completed:</label>" +
				"<select id='completeStatusId' class='form-control' style='width : 100px;'>"+
					selectOption +
				"</select>" +
			"</div>" +
		"</div><br/>"+
		"<div class='row'>"+
			"<div class='col-md-12 col-centered'>"+
				"<span>" +
					"<button type='button' id='todoUpdate' class='todoUpdate'>Update Task</button>" +
					"&nbsp;&nbsp;" +
					"<button type='button' class='executeUpdateCancelBtn'>Cancel</button>" +
				"</span>"+
			"</div>"+
		"</div>";
					 	
					 	
	$('#updateFormDiv').html(updateForm);
	console.log("finished building update html");
	
});

$(document).on('click', '.todoUpdate', function(){
	console.log("executing onclick todoUpdate function");
	
	var userBackBean = {};
	userBackBean["name"] = $('#owners').val();
	var todoBackBean = {};
	todoBackBean["id"] = $('#updateId').val();
	todoBackBean["taskName"] = $('#updateTaskNameId').val();
	todoBackBean["complete"] = $('#completeStatusId').val();
	todoBackBean["user"] = userBackBean;
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/todo/update",
		data : JSON.stringify(todoBackBean),
		dataType : "json",
		cache:false,
		success : function(result){
			
			if (result.status = "success"){
				console.log("successfully processed todoUpdate");
				myTaskTable.ajax.reload();
				$('#updateRow').hide();
				$('#indexRow').show();
			}
			else {
				console.log("failure to process todoUpdate");
				$('#updateRow').hide();
				$('#indexRow').show();
				alert("failed to process update");
			}
		},
		error:function(e){
			alert("Errors");
			console.log("error receiving data from backend: " + e);
		}
	});
	console.log("finished executing update function");
});


$(document).on('click', '.executeUpdateCancelBtn', function(){
	console.log("executing onclick executeUpdateCancelBtn function");
	window.location.href = 'index';
});




