/////////////////////////// Document ///////////////////////////

/*** Document loader ***/
$(window).bind("load", function() {
	$(document).ready(function () {
		$('.loaderoverlay').fadeOut(800);
	});
	$('#onload-popup').modal('show');
});


/** Popup Close **/

$(document).click(function (e) {
		var q = $(e.target).closest('.sidebar').length
		if (!q) {
			$(".lefttop-nav-dropdown").css("display", "none");
			$('.lefttop-icon').removeClass('active');
		}
});
	
	
$(function() {
/*** Back To Top ***/
$(window).scroll(function () {
	if ($(this).scrollTop() < 100) {
		$('#totop').fadeOut();
	} else
	{
		$('#totop').fadeIn();
	}
});
$('#totop').on('click', function () {
	$('html, body').animate({scrollTop: 0}, 'fast');
	return false;
});

// Form 
$('input, textarea').placeholder();

// Scroll
$("html").niceScroll();
$(".sidebar").niceScroll({cursorcolor:"#3fabdf",background: "#0d1218",cursorfixedheight: 100,scrollspeed :150}).resize();





// Main Nav
$("#demo1").navgoco({accordion: true});

//Responsive Menu
$(".rmenu-icon").click(function (event) {
	if($(".sidebar").hasClass("active"))
	{
		$('.sidebar').removeClass('active');
	}
	else 
	{
		$('.sidebar').addClass('active');
	}
	//$('.sidebar').addClass('active');
});

 // Tooltips
$('.cust-tooltip').tipsy({gravity: 's'});// nw | n | ne | w | e | sw | s | se

//Date range selector
$('#date-range').daterangepicker(null, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
	$('#display-cutdate').html(start.format('DD/MM/YY') + ' - ' + end.format('DD/MM/YY'));
});
	  
//Select All
$('.Select-all').change(function() {
	var checkboxes = $(this).closest('form').find(':checkbox');
	if($(this).is(':checked')) {
		checkboxes.attr('checked', 'checked');
	} else {
		checkboxes.removeAttr('checked');
	}
});
	
// Select All viceversa  
$(".persona-check").click(function(){
	if($(".persona-check").length == $(".persona-check:checked").length) {
		$("#All-persona").attr("checked", "checked");
	} else {
		$("#All-persona").removeAttr("checked");
	}

});
$(".roaming-check").click(function(){
	if($(".roaming-check").length == $(".roaming-check:checked").length) {
		$("#All-Roaming").attr("checked", "checked");
	} else {
		$("#All-Roaming").removeAttr("checked");
	}

});
$(".device-check").click(function(){
	if($(".device-check").length == $(".device-check:checked").length) {
		$("#Device-Type").attr("checked", "checked");
	} else {
		$("#Device-Type").removeAttr("checked");
	}

});
	

// Left Nav Click
$(".lefttop-icon").click(function(){
	var $div = $(this).next('.lefttop-nav-dropdown').toggle();
	$(".lefttop-nav-dropdown").not($div).hide();
	var $div2=$(this).toggleClass('active');
	$('.lefttop-icon').not($div2).removeClass('active');
});

// flip bubble
$(".viewtrend-link").click(function(){
  $(this).parents().closest(".card").toggleClass("flipped");
  return false;
});

// toggle bookmark state  bookmark-content   
$(".bookmark-unactive").click(function(){
	if($(this).hasClass('active'))
	{
		$(this).removeClass('active');
	}
	else{
		$(this).parents('.segment-charts').next('.bookmark-panel').show();
		$(this).addClass('active');
	}
});
$(".close-bookmark").click(function(){
  	$(this).parent().hide();
});

// Hidden table in top10
$(".viewtable-link").click(function(){
  $(this).next().show();
  $(this).hide();
});
$(".hide-table").click(function(){
	$('.viewtable-link').show();
  	$(this).parent().hide();
});

//dropdown stay for writing
$(".stay-dd").click(function(e) {
       e.stopPropagation();
    });
});


//custom select box 
$('.select').selectric();





