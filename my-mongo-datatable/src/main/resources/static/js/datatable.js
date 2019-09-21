
$(document).ready( function () {
	var table = $('#taskTable')
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
						  return "<div align='center'><span><button id='delete" +full.Id+"' value='"+meta.row+"'>Delete</button></span>&nbsp;&nbsp;<span><button>Update</button></span></div>";
					  }
				  }
			]
	 })
});

//Function that creates the configuration object for use by the datatables buttons extension
//function getButtonConfig(buttonTypes){
//	let defaultConfig = [];
//	
//	// Iterate over the array of button types that are passed in and call the appropriate configuration function
//	buttonTypes.forEach(function(buttonType){
//		switch(buttonType){
//		case CSV_BUTTON:
//			defaultConfig.push(getCsvButtonConfig());
//			break;
//		default:
//			break;
//		}
//	})
//	
//	return defaultConfig;
//}
//
//function getCsvButtonConfig(){
//	if(controller === "R2AppMgr"){
//		return {				
//			  "extend" :'csv',     
//			  "text" : "Download CSV",
//			  "fieldSeparator" : "|",
//			  "extension" : ".txt",
//			  "exportOptions" : {
//				  "columns" : [0,1,2,3,4,5,6]
//			  }
//		}
//	}
//}




/*$(document).ready( function () {
	var table = $('#taskTable')
	.DataTable(
		{
		"sAjaxSource": "/todoDataTable",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "data": "id"},
		          { "data": "taskName" },
				  { "data": "owner" },
				  { "data": "createDate" },
				  { "data": "complete" },
				  { "data": "completeDate" }
			]
	 })
});
*/



