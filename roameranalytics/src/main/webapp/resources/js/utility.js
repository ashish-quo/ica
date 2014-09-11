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
		
		utilityService.getParamsFromFilter = function (filters) {
			// transform attribute filters
			var attrs = '';
			for (var key in filters.attributes) {
				  if (filters.attributes.hasOwnProperty(key)) {
				    attrs += '' + key + ':' + $j.map(filters.attributes[key], function(obj) {
				    	return obj.catId;
				    }).join(",")+ "#";
				  }
			}
			attrs = attrs.substring(0, attrs.length - 1);
			
			// transform persona filter
			var personas = $j.map(filters.personas, function(obj) {
		    	return obj.id;
		    }).join(",");
			
			// transform countries filter
			var countries = $j.map(filters.countries, function(obj) {
		    	return obj.id;
		    }).join(",");
			
			var tempAttrs = '';
			for (var key in filters.tempAttributes) {
				  if (filters.tempAttributes.hasOwnProperty(key)) {
					  tempAttrs += '' + key + ':' + filters.tempAttributes[key]+ "#";
				  }
			}
			
			
			tempAttrs = tempAttrs.substring(0, tempAttrs.length - 1);
			var params = { 'dateRangeFrom' : filters.dateRangeFrom,
				'dateRangeTo': filters.dateRangeTo,
				'attributes' : attrs,
				'countries' : countries,
				'personas': personas,
				'tempAttributes' : tempAttrs
			}
			return params;
		};
		return utilityService;
	}]);
	
})();