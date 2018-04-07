$(document).ready(function () {
    $('#roleList').dataTable({
	responsive: true,
	paging: false,
	searching: false,
	info: false,
	"columnDefs": [
	    {"orderable": false, "targets": [1]},
	    {"searchable": false, "targets": [1]}
	],
	"language": {
	    "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Portuguese-Brasil.json"
	}
    });
});

$('.selectRole').selectpicker({
  size: 5
});

