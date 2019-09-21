$(document).ready( function () {
	var table = $('#taskTable').DataTable({
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


/*
function init(){
	 var table = $('#taskTable').DataTable({
			"sAjaxSource": "/todoDataTable",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "taskName" },
				  { "mData": "owner" },
				  { "mData": "complete" }
				  { "mData": "completedate" },
				  { "mData": "createDate" }
			]
	 })	
}
*/
