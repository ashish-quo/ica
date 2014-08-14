var $j = jQuery.noConflict();

$j( document ).ready(function() {
	//Date range selector
	$j('#date-range').daterangepicker(null, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
		$j('#display-cutdate').html(start.format('DD/MM/YY') + ' - ' + end.format('DD/MM/YY'));
	});
	// Scroll
	$j("html").niceScroll();
	$j(".sidebar").niceScroll({cursorcolor:"#3fabdf",background: "#0d1218",cursorfixedheight: 100,scrollspeed :150}).resize();
});