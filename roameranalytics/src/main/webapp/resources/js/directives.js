( function () {
	var appDirectives = angular.module("app.directives",[]);
	
	/**
	 * Directive for roaming trends
	 */
	appDirectives.directive('trends', ['$rootScope',function($rootScope) {
	    return {
	      restrict: 'E',
	      templateUrl: $rootScope.roamType + '/trends',
	    };
	  }]);
	
	/**
	 * Directive for microsegment
	 */
	appDirectives.directive('microsegment', ['$rootScope',function($rootScope) {
	    return {
	      restrict: 'E',
	      templateUrl: $rootScope.roamType + '/microsegment',
	    };
	  }]);
	
	/**
	 * Directive for HeatMap
	 */
	appDirectives.directive('heatMap', ['$rootScope',function($rootScope) {
	    return {
	      restrict: 'E',
	      templateUrl: $rootScope.roamType + '/heatMap',
	    };
	  }]);
	
})();