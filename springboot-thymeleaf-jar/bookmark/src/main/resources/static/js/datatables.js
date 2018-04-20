$(document).ready(function () {
    $('#dataList').dataTable({
	responsive: true,
	"columnDefs": [
	    {"orderable": false, "targets": [0]},
	    {"searchable": false, "targets": [0]}
	],
	"order": [],
	"language": {
	    "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json"
	}
    });
});