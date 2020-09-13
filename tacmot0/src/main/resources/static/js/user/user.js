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

//---update functionality

$(document).on('click', '.updateButton', function(){
	console.log("executing onclick user updateButton function");
	var $btn=$(this);
    var $tr=$btn.closest('tr');
    var dataTableRow=myUserTable.row($tr[0]); 
    var rowData=dataTableRow.data();
    console.log('user values - id: '+rowData.id+' userName: '+rowData.name+' fullName: '+rowData.fullName + ' email: ' + rowData.email );
    
	$('#indexRow').hide();
	$('#updateRow').show();

	var updateForm = 
		"<input id='updateId' type='hidden' class='form-control' name='id' value='"+rowData.id+"'/>"+
		"<div class='row'>" +
			"<div class='col-md-4'>"+
				"<label for='updateUserNameId'>User Name (Unique): </label>"+
				"<input id='updateUserNameId' type='text' class='form-control' name='userName' "+
				" readonly value='"+rowData.name+"'/>"+
			"</div>"+
			"<div class='col-md-4'>" +
				"<label for='updateUserFirstNameId'>First Name: </label>" +
				"<input id='updateUserFirstNameId' type='text' class='form-control' name='firstName' "+
				"value='"+rowData.firstName+"'/>"+
			"</div>" +
			"<div class='col-md-4'>" +
				"<label for='updateUserLastNameId'>Last Name: </label>" +
				"<input id='updateUserLastNameId' type='text' class='form-control' name='lastName' "+
				"value='"+rowData.lastName+"'/>"+
			"</div>" +
		"</div></br>" +
		"<div class='row'>" +
			"<div class='col-md-5'>" +
				"<label for='updateUserPhoneId'>Phone Number:</label>" +
				"<input id='updateUserPhoneId' type='text' class='form-control' name='phone' "+
				"value='"+rowData.phone+"'/>"+
			"</div>" +
			"<div class='col-md-5'>" +
				"<label for='updateUserEmailId'>Email Address:</label>" +
				"<input id='updateUserEmailId' type='text' class='form-control' name='email' "+
				"value='"+rowData.email+"'/>"+
			"</div>" +
		"</div><br/>"+
		"<div class='row'>"+
			"<div class='col-md-12 col-centered'>"+
				"<span>" +
					"<button type='button' id='userUpdateId' class='userUpdate'>Update User</button>" +
					"&nbsp;&nbsp;" +
					"<button type='button' class='executeUpdateCancelBtn'>Cancel</button>" +
				"</span>"+
			"</div>"+
		"</div>";
					 	
					 	
	$('#updateFormDiv').html(updateForm);
	console.log("finished building update html");
	
});

$(document).on('click', '.userUpdate', function(){
	console.log("executing onclick todoUpdate function");
	var userBackBean = {};
	userBackBean["id"] = $('#updateId').val();
	userBackBean["name"] = $('#updateUserNameId').val();
	userBackBean["firstName"] = $('#updateUserFirstNameId').val();
	userBackBean["lastName"] = $('#updateUserLastNameId').val();
	userBackBean["phone"] = $('#updateUserPhoneId').val();
	userBackBean["email"] = $('#updateUserEmailId').val();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/user/update",
		data : JSON.stringify(userBackBean),
		dataType : "json",
		cache:false,
		success : function(result){
			
			if (result.status = "success"){
				console.log("successfully processed userUpdate");
				myUserTable.ajax.reload();
				$('#updateRow').hide();
				$('#indexRow').show();
			}
			else {
				console.log("failure to process userUpdate");
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





