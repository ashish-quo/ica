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
$(".sidebar, .track-data-area").niceScroll({cursorcolor:"#3fabdf",background: "#0d1218",cursorfixedheight: 100,scrollspeed :150}).resize();






// Main Nav
$("#demo1").navgoco({accordion: true});

$("#demo2").navgoco({accordion2: true});

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
$('.cust-tooltip-dn').tipsy({gravity: 'n'});// nw | n | ne | w | e | sw | s | se

//Date range selector
$('#date-range').daterangepicker(null, function(start, end, label) {
	console.log(start.toISOString(), end.toISOString(), label);
	$('#display-cutdate').html(start.format('DD/MM/YY') + ' - ' + end.format('DD/MM/YY'));
});
	  
//Select All Left checkbox
$('.Select-all').change(function() {
	var checkboxes = $(this).closest('form').find(':checkbox');
	if($(this).is(':checked')) {
		checkboxes.attr('checked', 'checked');
	} else {
		checkboxes.removeAttr('checked');
	}
});

$('.Select-all-inn').change(function() {
	var checkboxes = $(this).closest('.category-inner').find(':checkbox');
	if($(this).is(':checked')) {
		checkboxes.attr('checked', 'checked');
	} else {
		checkboxes.removeAttr('checked');
	}
});


$('.Travel-all-inn').change(function() {
	var checkboxes = $(this).closest('.category-inner').find(':checkbox');
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
$(".network-check").click(function(){
	if($(".network-check").length == $(".network-check:checked").length) {
		$("#All-Network").attr("checked", "checked");
	} else {
		$("#All-Network").removeAttr("checked");
	}

});
$(".roaming-check").click(function(){
	if($(".roaming-check").length == $(".roaming-check:checked").length) {
		$("#All-Roaming").attr("checked", "checked");
	} else {
		$("#All-Roaming").removeAttr("checked");
	}

});
$(".domestic-check").click(function(){
	if($(".domestic-check").length == $(".domestic-check:checked").length) {
		$("#All-Domestic").attr("checked", "checked");
	} else {
		$("#All-Domestic").removeAttr("checked");
	}

});

$(".prepaid-check").click(function(){
	if($(".prepaid-check").length == $(".prepaid-check:checked").length) {
		$("#All-Prepaid-Type").attr("checked", "checked");
	} else {
		$("#All-Prepaid-Type").removeAttr("checked");
		$("#All-Payment").removeAttr("checked");
	}

});


$(".postpaid-check").click(function(){
	if($(".postpaid-check").length == $(".postpaid-check:checked").length) {
		$("#All-Postpaid-Type").attr("checked", "checked");
	} else {
		$("#All-Postpaid-Type").removeAttr("checked");
		$("#All-Payment").removeAttr("checked");
	}

});

$(".postpaid-check,.prepaid-check, .Select-all-inn").click(function(){
	if($(".Select-all-inn").length == $(".Select-all-inn:checked").length) {
			$("#All-Payment").attr("checked",'checked');
		}
		else{
			$("#All-Payment").removeAttr("checked");
			}
});



$(".device-check").click(function(){
	if($(".device-check").length == $(".device-check:checked").length) {
		$("#Device-Type").attr("checked", "checked");
	} else {
		$("#Device-Type").removeAttr("checked");
	}

});
	
//overview left page checkbox start

$(".bef-tag-check").click(function(){
	if($(".bef-tag-check").length == $(".bef-tag-check:checked").length) {
		$("#All-Before-travel").attr("checked", "checked");
	} else {
		$("#All-Before-travel").removeAttr("checked");
		$("#All-Travel").removeAttr("checked");
	}

});


$(".upon-tag-check").click(function(){
	if($(".upon-tag-check").length == $(".upon-tag-check:checked").length) {
		$("#All-Upon-landing").attr("checked", "checked");
	} else {
		$("#All-Upon-landing").removeAttr("checked");
		$("#All-Travel").removeAttr("checked");
	}

});

$(".bef-tag-check,.upon-tag-check, .Travel-all-inn").click(function(){
	if($(".Travel-all-inn").length == $(".Travel-all-inn:checked").length) {
			$("#All-Travel").attr("checked",'checked');			
		}
		else{
			$("#All-Travel").removeAttr("checked");
			
			}
});



//overview checkbox end


// overview tag box hide, show


$('#All-Before-travel').change(function() {
  if ($(this).is(':checked')) {
    $("#bef-tag-1, #bef-tag-2").show()
  } else {
    $("#bef-tag-1, #bef-tag-2").hide()
  }
});

$('#All-Upon-landing').change(function() {
  if ($(this).is(':checked')) {
    $("#up-tag-1, #up-tag-2").show()
  } else {
    $("#up-tag-1, #up-tag-2").hide()
  }
});

$('#All-Travel').change(function() {
  if ($(this).is(':checked')) {
    $("#bef-tag-1,#bef-tag-2,#up-tag-1,#up-tag-2").show()
  } else {
    $("#bef-tag-1,#bef-tag-2,#up-tag-1,#up-tag-2").hide()
  }
});

$('#All-Travel').change(function() {
  if ($(this).is(':checked')) {
    $("#bef-tag-1,#bef-tag-2,#up-tag-1,#up-tag-2").show()
  } else {
    $("#bef-tag-1,#bef-tag-2,#up-tag-1,#up-tag-2").hide()
  }
});


$('#Tag1').change(function() {
  if ($(this).is(':checked')) {
    $("#bef-tag-1").show()
  } else {
    $("#bef-tag-1").hide()
  }
});

$('#Tag2').change(function() {
  if ($(this).is(':checked')) {
    $("#bef-tag-2").show()
  } else {
    $("#bef-tag-2").hide()
  }
});

$('#Tag3').change(function() {
  if ($(this).is(':checked')) {
    $("#up-tag-1").show()
  } else {
    $("#up-tag-1").hide()
  }
});

$('#Tag4').change(function() {
  if ($(this).is(':checked')) {
    $("#up-tag-2").show()
  } else {
    $("#up-tag-2").hide()
  }
});


// overview tag box hide, show end

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


// Tracker pop checkbox  

$(".tracker-check").click(function(){
	if($(this).prop('checked')==true) {		
		$(this).parents('.track-data li').addClass("tracker-active");
	} else if($(this).prop('checked')==false){
		$(this).parents('.track-data li').removeClass("tracker-active");
	}

});


// pop trends highcharts

$(function () {

	$('#roamer-ft-zoom').modalPopLite({ openButton: '#roamer-ft-btn', closeButton: '#close-btn', isModal: true });
	$('#roamer-back-zoom').modalPopLite({ openButton: '#roamer-bk-btn', closeButton: '#close-btn', isModal: true });
	$('#voice-ft-zoom').modalPopLite({ openButton: '#voice-ft-btn', closeButton: '#close-btn', isModal: true });
	$('#voice-back-zoom').modalPopLite({ openButton: '#voice-bk-btn', closeButton: '#close-btn', isModal: true });
	$('#data-ft-zoom').modalPopLite({ openButton: '#data-ft-btn', closeButton: '#close-btn', isModal: true });
	$('#data-back-zoom').modalPopLite({ openButton: '#data-bk-btn', closeButton: '#close-btn', isModal: true });
	$('#sms-ft-zoom').modalPopLite({ openButton: '#sms-ft-btn', closeButton: '#close-btn', isModal: true });
	$('#sms-back-zoom').modalPopLite({ openButton: '#sms-bk-btn', closeButton: '#close-btn', isModal: true });

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

// Tag Created Section delete

$(".before-close-icon").click(function() {
	$(this).closest(".before-travel").find(".before-travel-section").hide();	
});

//favourite icon
$(".panelfav-icon").click(function(e) {
       $(this).toggleClass('active');
});


// show, hide trend Chart

$('.roamers-btn2').click(function() {
	$(this).closest('.topChart-panel-tr').find('.back-top').addClass("top-active");
	$(this).closest('.topChart-panel-tr').find('.front-top').hide();
});
$('.roamers-btn1').click(function() {
	
	$(this).closest('.topChart-panel-tr').find('.front-top').show();
	$(this).closest('.topChart-panel-tr').find('.back-top').removeClass("top-active");
});


});

// roamer, trends page active 
$('.angle_btn').click(function(event){    
	$(this).parent().find('.angle_btn').removeClass('active-scale');
	$(this).addClass('active-scale');     
	event.preventDefault();
});

// Home page show, hide script 

 $(document).ready(function(){
        $('.heavy-hitter-opt').live('click',function() 
        {
            if ($(this).is(':checked')) {
                $(this).closest('.bubble-label-chart').find('.visback ').addClass("top-active");
				$(this).closest('.bubble-label-chart').find('.visfront').hide();
            } else {
                $(this).closest('.bubble-label-chart').find('.visfront').show();
				$(this).closest('.bubble-label-chart').find('.visback').removeClass("top-active");
            } 
        });
    });


// hide, show micro segment tracker 
$(".compare-custom").click(function() {
	$(".compare-between").fadeOut(0);
	$(".compare-custom-box").css("display", "block").fadein(1500);
});
$(".compare_btn button.unactive").click(function() {
	$(".compare-custom-box").fadeOut(0);
	$(".compare-between").css("display", "block").fadein(1500);
});



// hide, show home page map 
$("#map-container-roamer").hide();
$(".all-blue-map").click(function() {
	$("#map-container").fadeOut(0);
	$("#map-container-roamer").css("display", "block").fadein(1500);
});
$("#usage").click(function() {
	$("#map-container-roamer").fadeOut(0);
	$("#map-container").css("display", "block").fadein(1500);
});

//custom select box 
$('.select').selectric();





