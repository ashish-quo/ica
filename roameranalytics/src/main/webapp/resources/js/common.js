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
//	//Date range selector
//	$j('#date-range').daterangepicker(null, function(start, end, label) {
//		console.log(start.toISOString(), end.toISOString(), label);
//		$j('#display-cutdate').html(start.format('DD/MM/YY') + ' - ' + end.format('DD/MM/YY'));
//	});

	// Main Nav
	$j("#demo1").navgoco({accordion: false});
	
	// Scroll
	$j("html").niceScroll();
	$j(".sidebar").niceScroll({cursorcolor:"#3fabdf",background: "#0d1218",cursorfixedheight: 100,scrollspeed :150}).resize();
});

//Select country
$j(document).on( "change", ".country-chk", function() {
	if($j(this).is(':checked')) {
		$j(this).attr('checked', 'checked');
	} else {
		$j(this).removeAttr('checked');
	}
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
	if($j(this).is(':checked')) {
		$j(this).attr('checked', 'checked');
	} else {
		$j(this).removeAttr('checked');
	}
	var parent = $j(this).closest('form')
	if($j(parent).find('.sub-check').length == $j(parent).find(".sub-check:checked").length) {
		$j(parent).find('.select-all').attr("checked", "checked");
	} else {
		$j(parent).find('.select-all').removeAttr("checked");
	}

});
// Resize the scroll bar on mouse over
$j(document).on('mouseover',".sidebar" ,function(){
	$j(".sidebar").getNiceScroll().resize();
});

$j(document).on("mouseover", "#mainContent" , function(){
    //$j("body").css("overflow", "auto");        
    $j("html").getNiceScroll().resize();
});