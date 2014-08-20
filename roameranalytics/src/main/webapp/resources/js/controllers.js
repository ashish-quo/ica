(function() {
	var appControllers = angular.module("app.controllers", [ "highcharts-ng" ]);

	/**
	 * Main Controller for global actions
	 */
	appControllers.controller('MainController', function($scope) {
		$scope.tabIndex = 0;
		$scope.isTrendTab = function() {
			return $scope.tabIndex == 0
		};
		$scope.isMicroSegmentTab = function() {
			return $scope.tabIndex == 1
		};
		$scope.isPredictTab = function() {
			return $scope.tabIndex == 2
		};
		$scope.showTrends = function() {
			$scope.tabIndex = 0;
		};
		$scope.showMicroSegment = function() {
			$scope.tabIndex = 1;
		};
		$scope.showPredict = function() {
			$scope.tabIndex = 2;
		};
	});

	/**
	 * Main Controller for global actions
	 */
	appControllers.controller('SidebarController',['$scope','$http', function($scope,$http) {
		$scope.startDate = "01/01/14";
		$scope.endDate = "07/01/14";
		$http.get('getAttributes').success(function(data) {
			$scope.attributes = data;
			$scope.defaultAttributes = $scope.attributes['default'];
			$scope.hiddenAttributes = $scope.attributes['hidden'];
		});
		
		$http.get("getCountries").success(function (data) {
			$scope.countries = data;
		});
		
		function appendZero(num) {
			if (num < 10) {
				return "0"+num;
			} else 
				return ""+num;
		}
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
		    return rangeIsFrom + " - " + rangeIsTo;
		};
		$scope.thisWeekRange = function() {
			$j('#display-cutdate').html(getDateRangeOfWeek(new Date().getWeek()));
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
			$j('#display-cutdate').html(getDateRangeOfWeek(new Date().getWeek() - 1));
		};
		
		$scope.thisMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			
			var start = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			var end = appendZero(now.getDate()) + "/" + appendZero(now.getMonth()+1) + "/" + (""+now.getFullYear()).slice(2);
			$j('#display-cutdate').html(start + " - " + end);
		};
		
		$scope.lastMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth()-1,1);
			var endTemp = new Date(now.getFullYear(),now.getMonth(),0);
			
			var start = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			var end = appendZero(endTemp.getDate()) + "/" + appendZero(endTemp.getMonth()+1) + "/" + (""+endTemp.getFullYear()).slice(2);
			$j('#display-cutdate').html(start + " - " + end);
		};
		
		$scope.thisQuarter = function() {
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var quarterEndMonth = quarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			var start = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			var end = appendZero(endTemp.getDate()) + "/" + appendZero(endTemp.getMonth()+1) + "/" + (""+endTemp.getFullYear()).slice(2);
			$j('#display-cutdate').html(start + " - " + end);
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
			
			var start = appendZero(startTemp.getDate()) + "/" + appendZero(startTemp.getMonth()+1) + "/" + (""+startTemp.getFullYear()).slice(2);
			var end = appendZero(endTemp.getDate()) + "/" + appendZero(endTemp.getMonth()+1) + "/" + (""+endTemp.getFullYear()).slice(2);
			$j('#display-cutdate').html(start + " - " + end);
		};
		
		$scope.filterHiddenAttr = function(text) {
			if ($scope.query == null || $scope.query.displayText == '' )
				return true;
			else {
				return text.toUpperCase().indexOf($scope.query.displayText.toUpperCase()) != -1;
			}
		};
		
		$scope.toggleDefaultAttr = function(e) {
			$j(e.srcElement).closest("li.nav-dropdown").toggleClass("open");
			$j(e.srcElement).closest("li.nav-dropdown").find("ul").toggle();
		}
	}]);
	
	/**
	 * Trend controller for trend screen actions
	 */

	appControllers.controller('TrendController', function($scope) {
		$scope.trendTabIndex = 0;
		$scope.roamerCountChartConfig = {
			options : {
				chart : {
					type : 'line',
					zoomType : 'x'
				}
			},
			series : [ {
				data : [ 10, 15, 12, 8, 7, 1, 1, 19, 15, 10 ]
			} ],
			title : {
				text : 'Hello'
			},
			xAxis : {
				currentMin : 0,
				currentMax : 10,
				minRange : 1
			},
			loading : false
		};

		$scope.showHeatMap = function() {
			$scope.trendTabIndex = 0;
		};
		$scope.showTopTen = function() {
			$scope.trendTabIndex = 1;
		};
		$scope.showRoamingTrend = function() {
			$scope.trendTabIndex = 2;
		};

		$scope.isHeatMapSelected = function() {
			return $scope.trendTabIndex == 0;
		};
		$scope.isTopTenSelected = function() {
			return $scope.trendTabIndex == 1;
		};
		$scope.isRoamingTrendSelected = function() {
			return $scope.trendTabIndex == 2;
		};
	});

})();
