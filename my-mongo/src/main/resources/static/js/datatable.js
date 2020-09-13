$(document).ready( function () {
	var table = $('#taskTable').DataTable({
			"sAjaxSource": "/todoDataTable",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id"},
		          { "mData": "taskName" },
				  { "mData": "owner" },
				  { "mData": "createDate" },
				  { "mData": "complete" },
				  { "mData": "completeDate" }
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
