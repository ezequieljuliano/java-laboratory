$(document).ready(function () {
    $('#roleList').dataTable({
	responsive: true,
	"columnDefs": [
	    {"orderable": false, "targets": [2, 3]},
	    {"searchable": false, "targets": [2, 3]}
	],
	"language": {
	    "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json"
	}
    });
});

