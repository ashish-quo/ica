(function(){
	var utillity = angular.module("app.utility",[]);
	utillity.factory("util", [function () {
		utilityService = {};
		
		utilityService.normalizeDate = function(num) {
			if (num < 10) {
				return "0"+num;
			} else 
				return ""+num;
		};
		utilityService.getDateString = function (day, month, year) {
			return utilityService.normalizeDate(day) + "/" 
			+ utilityService.normalizeDate(month) + "/" 
			+ ("" + year).slice(2);
		};
		// gives date range start and end of the week by week number
		utilityService.getDateRangeOfWeek = function getDateRangeOfWeek(weekNo){
		    var d1 = new Date();
		    numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
		    d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
		    var weekNoToday = d1.getWeek();
		    var weeksInTheFuture = eval( weekNo - weekNoToday );
		    d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
		    var rangeIsFrom = utilityService.getDateString(d1.getDate(),d1.getMonth()+1,d1.getFullYear());
		    d1.setDate(d1.getDate() + 6);
		    var rangeIsTo = utilityService.getDateString(d1.getDate(),d1.getMonth()+1,d1.getFullYear());
		    return {
		    		"from":rangeIsFrom,
		    		"to": rangeIsTo
		    }
		};
		
		return utilityService;
	}]);
	
})();