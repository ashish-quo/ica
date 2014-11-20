(function() {
	var appControllers = angular.module("app.main", []);
	
	/**
	 * Main Controller for global actions
	 */
	appControllers.controller('MainController',
			['$scope', '$rootScope','httpService', 'pendingRequests', function($scope, $rootScope,httpService, pendingRequests) {
		
		
		$rootScope.tabIndex = 0;
		
		$rootScope.isHomeTab = function() {
			return $rootScope.tabIndex == 0
		};
		$rootScope.isTrendTab = function() {
			return $rootScope.tabIndex == 1;
		};
		$rootScope.isMicroSegmentTab = function() {
			return $rootScope.tabIndex == 2;
		};
		$rootScope.isBeforeTravelTab = function() {
			return $rootScope.tabIndex == 3;
		};
		
		$rootScope.isUponLandingTab = function() {
			return $rootScope.tabIndex == 4;
		};
		
		$rootScope.showHome = function() {
			$rootScope.error = '';
			$rootScope.tabIndex = 0;
		};
		$rootScope.showTrends = function() {
			$rootScope.error = '';
			pendingRequests.cancelAll(); //added by smruti for pending request cancel
			$rootScope.tabIndex = 1;
		};
		$rootScope.showMicroSegment = function() {
			$rootScope.error = '';
			pendingRequests.cancelAll(); //added by smruti for pending request cancel
			$rootScope.tabIndex = 2;
		};
		$rootScope.showBeforeTravel = function() {
			$rootScope.tabIndex = 3;
		};
		$rootScope.showUponLanding = function() {
			$rootScope.tabIndex = 4;
		};
		$rootScope.error = '';
		$j('#error').show();
	}]);

})();
