( function () {
	var appDirectives = angular.module("app.directives",[]);
	
	/**
	 * Directive for roaming trends
	 */
	appDirectives.directive('trends', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'trends',
	    };
	  });
	
	/**
	 * Directive for microsegment
	 */
	appDirectives.directive('microsegment', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'microsegment',
	    };
	  });
	
	/**
	 * Directive for HeatMap
	 */
	appDirectives.directive('heatMap', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'heatMap',
	    };
	  });
	
})();