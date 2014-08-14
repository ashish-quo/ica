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
		$http.get('getAttributes').success(function(data) {
			$scope.attributes = data;
			$scope.defaultAttributes = $scope.attributes['default'];
			$scope.hiddenAttributes = $scope.attributes['hidden'];
		});
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
