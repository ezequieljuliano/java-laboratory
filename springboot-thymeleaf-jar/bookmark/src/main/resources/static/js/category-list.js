$(document).ready(function () {
    $('#categoryList').dataTable({
	responsive: true,
	"columnDefs": [
	    {"orderable": false, "targets": [1, 2]},
	    {"searchable": false, "targets": [1, 2]}
	],
	"language": {
	    "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json"
	}
    });
});

