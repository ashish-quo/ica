(function(){
	var sidebar = angular.module("app.sidebar",[]);
	/**
	 * Controller for sidebar actions
	 */
	sidebar.controller('SidebarController',['$scope','$rootScope', '$http', function($scope,$rootScope,$http) {
		$rootScope.filters = {
				attributes : {},
				countries : new Array(),
				dateRangeFrom : '',
				dateRangeTo : ''
		};
		
		//Date range selector
		$j('#date-range').daterangepicker(null, function(start, end, label) {
			$rootScope.dateRangeFrom = start.format('DD/MM/YY');
			$rootScope.dateRangeTo = end.format('DD/MM/YY');
			$rootScope.$apply();
		});
		
		// appends zero to date and months where value is less than 10
		function appendZero(num) {
			if (num < 10) {
				return "0"+num;
			} else 
				return ""+num;
		}
		
		// gives date range start and end of the week by week number
		function getDateRangeOfWeek(weekNo){
		    var d1 = new Date();
		    numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
		    d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
		    var weekNoToday = d1.getWeek();
		    var weeksInTheFuture = eval( weekNo - weekNoToday );
		    d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
		    var rangeIsFrom =  appendZero(d1.getDate()) +"/" + appendZero(eval(d1.getMonth()+1)) + "/" + (""+d1.getFullYear()).slice(2);
		    d1.setDate(d1.getDate() + 6);
		    var rangeIsTo =   appendZero(d1.getDate())+"/" + appendZero(eval(d1.getMonth()+1))+ "/" + (""+d1.getFullYear()).slice(2) ;
		    return {
		    		"from":rangeIsFrom,
		    		"to": rangeIsTo
		    }
		};
		var currentWeek = new Date().getWeek();
		var defaultDateRange = getDateRangeOfWeek(currentWeek);
		$rootScope.filters.dateRangeFrom = defaultDateRange.from;
		$rootScope.filters.dateRangeTo = defaultDateRange.to;
		
		$http.get('getAttributes').success(function(data) {
			$scope.attributes = data;
			$scope.defaultAttributes = $scope.attributes['default'];
			$scope.hiddenAttributes = $scope.attributes['hidden'];
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		$http.get("getCountries").success(function (data) {
			$scope.countries = data;
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		$scope.thisWeekRange = function() {
			var dateRange = getDateRangeOfWeek(new Date().getWeek());
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
		};
		
		$scope.lastWeekRange = function() {
			/**var now = new Date();
			var day = now.getDay()
			var startTemp = new Date(now.getFullYear(),now.getMonth()+1,now.getDate() 
					- now.getDay() + (now.getDay() == 0 ? -6:1));
			
			var startDate = startTemp.getDate() < 10 ? "0" + startTemp.getDate() : "" + startTemp.getDate();
			var startMonth = startTemp.getMonth() < 10 ? "0" + startTemp.getMonth() : "" + startTemp.getMonth();
			
			var nowDate = now.getDate() < 10 ? "0" + now.getDate() : "" + now.getDate();
			var nowMonth = (now.getMonth() +1) < 10 ? "0" + (now.getMonth() + 1) : "" + (now.getMonth()+1);
			
			var start = startDate + "/" + startMonth + "/" + (""+startTemp.getYear()).slice(1);
			var end = nowDate + "/" + nowMonth + "/" + (""+now.getYear()).slice(1);*/
			var dateRange = getDateRangeOfWeek(new Date().getWeek() - 1);
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
		};
		
		$scope.thisMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			
			$rootScope.filters.dateRangeFrom = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			$rootScope.filters.dateRangeTo = appendZero(now.getDate()) + "/" + appendZero(now.getMonth()+1) + "/" + (""+now.getFullYear()).slice(2);
		};
		
		$scope.lastMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth()-1,1);
			var endTemp = new Date(now.getFullYear(),now.getMonth(),0);
			
			$rootScope.filters.dateRangeFrom = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			$rootScope.filters.dateRangeTo = appendZero(endTemp.getDate()) + "/" + appendZero(endTemp.getMonth()+1) + "/" + (""+endTemp.getFullYear()).slice(2);
		};
		
		$scope.thisQuarter = function() {
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var quarterEndMonth = quarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			$rootScope.filters.dateRangeFrom = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			$rootScope.filters.dateRangeTo = appendZero(endTemp.getDate()) + "/" + appendZero(endTemp.getMonth()+1) + "/" + (""+endTemp.getFullYear()).slice(2);
		};
		
		$scope.lastQuarter = function() {
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var lastQuarter = quarter - 1;
			if (lastQuarter <= 0) {
				now.setFullYear(now.getFullYear() - 1);
				lastQuarter = 4;
			}
			var quarterEndMonth = lastQuarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			$rootScope.filters.dateRangeFrom = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			$rootScope.filters.dateRangeTo = appendZero(endTemp.getDate()) + "/" + appendZero(endTemp.getMonth()+1) + "/" + (""+endTemp.getFullYear()).slice(2);
		};
		
		$scope.filterHiddenAttr = function(id,text) {
			if ($scope.query == null || $scope.query.displayText == '' )
				return true;
			else {
				var result = text.toUpperCase().indexOf($scope.query.displayText.toUpperCase()) != -1;
				if (!result) {
					var nextSib = $j('#li_'+id).next("li.sub_"+id);
					if (nextSib.length > 0)
						result = true;
				}
				return result;
			}
		};
		
		$scope.updateAttributeFilter = function() {
			$rootScope.filters.attributes = {};
			$j("input.sub-check:checked").each(function () {
				var id = $j(this).attr("id").split("_");
				var attrId = id[0];
				var catId = id[1];
				var attrArray = $rootScope.filters.attributes[attrId];
				if (attrArray == null) {
					$rootScope.filters.attributes[attrId] = new Array();
				}
				$rootScope.filters.attributes[attrId].push(catId);
			});
			
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends",$rootScope.filters);
			}
		};
		$scope.updateCountryFilter = function() {
			$rootScope.filters.countries = new Array();
			$j("input.country-chk:checked").each(function () {
				var id = $j(this).attr("id")
				$rootScope.filters.countries.push(id);
			});
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends",$rootScope.filters);
			}
		};
		
		$scope.toggleDefaultAttr = function(e) {
			$j(e.srcElement).closest("li.nav-dropdown").toggleClass("open");
			$j(e.srcElement).closest("li.nav-dropdown").find("ul").toggle();
		}
	}]);
})();