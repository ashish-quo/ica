(function(){
	var sidebar = angular.module("app.sidebar",[]);
	/**
	 * Controller for sidebar actions
	 */
	sidebar.controller('SidebarController',
			['$scope','$rootScope', '$http', 'util', function($scope,$rootScope,$http,util) {
				
		$rootScope.filters = {
				attributes : {},
				countries : new Array(),
				dateRangeFrom : '',
				dateRangeTo : ''
		};
		
		//Date range selector
		$j('#date-range').daterangepicker(null, function(start, end, label) {
			$rootScope.filters.dateRangeFrom = start.format('DD/MM/YY');
			$rootScope.filters.dateRangeTo = end.format('DD/MM/YY');
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			$rootScope.$apply();
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		});
		var currentWeek = new Date().getWeek();
		var defaultDateRange = util.getDateRangeOfWeek(currentWeek);
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
			var dateRange = util.getDateRangeOfWeek(new Date().getWeek());
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		$scope.lastWeekRange = function() {
			var dateRange = util.getDateRangeOfWeek(new Date().getWeek() - 1);
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		$scope.thisMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			
			$rootScope.filters.dateRangeFrom =  util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(now.getDate(),now.getMonth()+1,now.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		$scope.lastMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth()-1,1);
			var endTemp = new Date(now.getFullYear(),now.getMonth(),0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		$scope.thisQuarter = function() {
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var quarterEndMonth = quarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
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
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0 && $rootScope.trendTabIndex == 2) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
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
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		$scope.toggleDefaultAttr = function(e) {
			$j(e.srcElement).closest("li.nav-dropdown").toggleClass("open");
			$j(e.srcElement).closest("li.nav-dropdown").find("ul").toggle();
		}
	}]);
})();