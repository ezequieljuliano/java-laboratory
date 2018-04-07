$(document).ready(function () {
    $('#userList').dataTable({
	responsive: true,
	"columnDefs": [
	    {"orderable": false, "targets": [5, 6]},
	    {"searchable": false, "targets": [5, 6]}
	],
	"language": {
	    "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json"
	}
    });
});

