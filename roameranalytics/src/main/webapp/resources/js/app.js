( function () {
	var app = angular.module("roamer-analytics",[]);
	app.controller('MainController', function ($scope) {
		$scope.tabIndex = 0;
		$scope.isTrendTab = function () {
			if ($scope.tabIndex == 0) 
				return true;
			return false;
		};
		$scope.isMicroSegmentTab = function () {
			if ($scope.tabIndex == 1) 
				return true;
			return false;
		};
		$scope.isPredictTab = function () {
			if ($scope.tabIndex == 2) 
				return true;
			return false;
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
	
	app.directive('trendHeader', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'trendHeader'
	    };
	  });
})();