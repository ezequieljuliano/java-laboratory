$(document).ready(function () {
    $('#bookmarkList').dataTable({
	responsive: true,
	"columnDefs": [
	    {"orderable": false, "targets": [3, 4]},
	    {"searchable": false, "targets": [3, 4]}
	],
	"language": {
	    "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json"
	}
    });
});

