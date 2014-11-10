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
	// Main Nav
	$j("#demo1").navgoco({accordion: true});
	$j(".home-backdrop").show();
	// Scroll
	$j("html").niceScroll();
	$j(".sidebar").niceScroll({cursorcolor:"#3fabdf",background: "#0d1218",cursorfixedheight: 100,scrollspeed :150}).resize();
	$j(".scrolldiv").niceScroll({cursorcolor:"#2a3542",background: "#2a3542",cursorfixedheight: 0,scrollspeed :150}).resize();
	$j(".scrolldiv").getNiceScroll().hide();
});

//Select country
$j(document).on( "change", ".country-chk", function() {
	if($j(this).is(':checked')) {
		$j(this).attr('checked', 'checked');
	} else {
		$j(this).removeAttr('checked');
	}
});


//Select country
$j(document).on( "change", "input#All-countries", function() {
	if($j(this).is(':checked')) {
		$j("input.select-all-country").attr("checked","checked");
	} else {
		$j("input.select-all-country").removeAttr("checked");
	}
});

//Select country
$j(document).on( "change", ".select-all-country", function() {
	var checked = $j("input.select-all-country:checked");
	var total = $j("input.select-all-country");
	if (checked.length == total.length) {
		$j("input#All-countries").attr('checked', 'checked');
	} else {
		$j("input#All-countries").removeAttr("checked");
	}
});
	
// Resize the scroll bar on mouse over
$j(document).on('mouseover',".sidebar .scrolldiv" ,function(){
	$j(".sidebar").getNiceScroll().resize();
	$j(".scrolldiv").getNiceScroll().resize();
});

//Resize the scroll bar on mouse over
$j(document).on('mouseover',".scrolldiv" ,function(){
	$j(".scrolldiv").niceScroll({cursorcolor:"#2a3542",background: "#2a3542",cursorfixedheight: 0,scrollspeed :150}).resize();
	$j(".scrolldiv").getNiceScroll().resize();
});

$j(document).on("mouseover", "#mainContent" , function(){
    //$j("body").css("overflow", "auto");        
    $j("html").getNiceScroll().resize();
});

//dropdown stay for writing    
$j(document).on("click", ".stay-dd", function(e) {
       e.stopPropagation();
});

//Home page show, hide script 

$j(document).ready(function(){
       $j('.heavy-hitter-opt').live('click',function() 
       {
           if ($j(this).is(':checked')) {
               $j(this).closest('.bubble-label-chart').find('.visback ').addClass("top-active");
				$j(this).closest('.bubble-label-chart').find('.visfront').hide();
           } else {
               $j(this).closest('.bubble-label-chart').find('.visfront').show();
				$j(this).closest('.bubble-label-chart').find('.visback').removeClass("top-active");
           } 
       });
       
       $j(".viewtrend-link").live('click',function(){
    	   if( $j(this).parents().closest(".card").hasClass("flipped")==true)
    		   $j(this).parents().closest(".card").removeClass("flipped");
    	   else
    		   $j(this).parents().closest(".card").addClass("flipped");
    	   return false;
    	 });
   });


