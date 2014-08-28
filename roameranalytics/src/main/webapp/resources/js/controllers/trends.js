(function(){
	var trends = angular.module("app.trends",[]);
	
	/**
	 * Controller for trend actions
	 */
	
	trends.controller('RoamingTrendController',
			['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
				
//				$j("#roamerCountChart").highcharts({
//					
//				});
				$scope.roamerCountChartConfig = {
						 options: {
					            chart: {
					                type: 'column'
					            }
					        },
					        xAxis: {
					               categories: ["A","B","C","D","E"]
				            },
					        series: [{
					            data: [10, 15, 12, 8, 7]
					        }],
					        title: {
					            text: 'Hello'
					        },
					        loading: false
				    }
				$rootScope.$on('refresh-roaming-trends', function (event, filters) {
					var attrs = '';
					for (var key in filters.attributes) {
						  if (filters.attributes.hasOwnProperty(key)) {
						    attrs += '' + key + ':' + filters.attributes[key].join(",")+ "#";
						  }
					}
					attrs = attrs.substring(0, attrs.length - 1);
					var params = { 'dateRangeFrom' : filters.dateRangeFrom,
						'dateRangeTo': filters.dateRangeTo,
						'attributes' : attrs,
						'countries' : filters.countries.join(",")
					}
					$http.get("getRoamingTrendsData", { 'params' : params}).success(function(result) {
						
						$scope.roamerCountChartConfig = {
								 options: {
							            chart: {
							                type: 'column'
							            }
							        },
							        xAxis: {
							               categories: result.roamersCountChart.dowCategoryList.map(function(value) {
							            	   return value.substring(0,3);
							               })
						            },
						            yAxis: {
						                min: 0,
						                title: {
						                    text: ''
						                }
						            },
							        series: result.roamersCountChart.dowSeriesList,
							        title: {
							            text: 'Roamers Count'
							        },
							        
						            plotOptions: {
						                column: {
						                    pointPadding: 0.2,
						                    borderWidth: 0
						                }
						            },
							        loading: false
						    }
					});
				});

	}]);
	
	trends.controller('TrendController',
			['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		$rootScope.trendTabIndex = 0;
		$scope.refresh = {
				'value' : 'true'
		};
		$scope.showHeatMap = function() {
			$rootScope.trendTabIndex = 0;
		};
		$scope.showTopTen = function() {
			$rootScope.trendTabIndex = 1;
		};
		$scope.showRoamingTrend = function() {
			$rootScope.trendTabIndex = 2;
		};

		$scope.isHeatMapSelected = function() {
			return $rootScope.trendTabIndex == 0;
		};
		$scope.isTopTenSelected = function() {
			return $rootScope.trendTabIndex == 1;
		};
		$scope.isRoamingTrendSelected = function() {
			return $rootScope.trendTabIndex == 2;
		};
		$scope.$on('refresh', function(event, args) {
			if ($scope.refresh == 'true') {
				$scope.refresh = 'false';
			} else {
				$scope.refresh = 'true';
			}
			
		});
	}]);
})();