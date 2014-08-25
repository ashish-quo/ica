(function() {
	var appControllers = angular.module("app.main", []);

	/**
	 * Main Controller for global actions
	 */
	appControllers.controller('MainController',
			['$scope', '$rootScope', function($scope, $rootScope) {
		$rootScope.tabIndex = 0;
		$rootScope.isTrendTab = function() {
			return $rootScope.tabIndex == 0
		};
		$rootScope.isMicroSegmentTab = function() {
			return $rootScope.tabIndex == 1
		};
		$rootScope.isPredictTab = function() {
			return $rootScope.tabIndex == 2
		};
		$rootScope.showTrends = function() {
			$rootScope.tabIndex = 0;
		};
		$rootScope.showMicroSegment = function() {
			$rootScope.tabIndex = 1;
		};
		$rootScope.showPredict = function() {
			$rootScope.tabIndex = 2;
		};
		$rootScope.error = '';
	}]);

})();
