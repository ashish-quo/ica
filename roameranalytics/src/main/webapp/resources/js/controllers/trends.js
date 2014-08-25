(function(){
	var trends = angular.module("app.trends",[]);
	/**
	 * Controller for trend actions
	 */
	
	trends.controller('RoamingTrendController',
			['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
				
				$rootScope.$on('refresh-roaming-trends', function (event, filters) {
					var attrs = '';
					for (var key in filters.attributes) {
						  if (filters.attributes.hasOwnProperty(key)) {
						    attrs += '' + key + ':' + filters.attributes[key].join(",")+ "#";
						  }
					}
					attrs = attrs.substring(0, attrs.length - 1);
					var params = { 'dateRangeFrom' : filters.dateRangeFrom,
						'dateRangeTo ': filters.dateRangeFrom,
						'attributes' : attrs,
						'countries' : filters.countries.join(",")
					}
					$http.get("getRoamingTrendsData", { 'params' : params}).success(function(result) {
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
						}
					});
				});
				$http.get("getAttributes").success(function (data) {
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
					};;
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