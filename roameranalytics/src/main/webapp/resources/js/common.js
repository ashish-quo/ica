var $j = jQuery.noConflict();
Date.prototype.getWeek = function() {
	  var date = new Date(this.getTime());
	   date.setHours(0, 0, 0, 0);
	  // Thursday in current week decides the year.
	  date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
	  // January 4 is always in week 1.
	  var week1 = new Date(date.getFullYear(), 0, 4);
	  // Adjust to Thursday in week 1 and count number of weeks from date to week1.
	  return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000
	                        - 3 + (week1.getDay() + 6) % 7) / 7);
}
$j( document ).ready(function() {
	//Date range selector
	$j('#date-range').daterangepicker(null, function(start, end, label) {
		console.log(start.toISOString(), end.toISOString(), label);
		$j('#display-cutdate').html(start.format('DD/MM/YY') + ' - ' + end.format('DD/MM/YY'));
	});

	// Main Nav
	$j("#demo1").navgoco({accordion: false});
	
	// Scroll
	$j("html").niceScroll();
	$j(".sidebar").niceScroll({cursorcolor:"#3fabdf",background: "#0d1218",cursorfixedheight: 100,scrollspeed :150}).resize();

});

//Select All check
$j(document).on( "change", ".select-all", function() {
	var checkboxes = $j(this).closest('form').find(':checkbox');
	if($j(this).is(':checked')) {
		checkboxes.attr('checked', 'checked');
	} else {
		checkboxes.removeAttr('checked');
	}
});

//Select All viceversa  
$j(document).on('change',".sub-check" ,function(){
	var parent = $j(this).closest('form')
	if($j(parent).find('.sub-check').length == $j(parent).find(".sub-check:checked").length) {
		$j(parent).find('.select-all').attr("checked", "checked");
	} else {
		$j(parent).find('.select-all').removeAttr("checked");
	}

});
$j(".roaming-check").click(function(){
	if($j(".roaming-check").length == $j(".roaming-check:checked").length) {
		$j("#All-Roaming").attr("checked", "checked");
	} else {
		$j("#All-Roaming").removeAttr("checked");
	}

});
$j(".device-check").click(function(){
	if($j(".device-check").length == $j(".device-check:checked").length) {
		$j("#Device-Type").attr("checked", "checked");
	} else {
		$j("#Device-Type").removeAttr("checked");
	}

});