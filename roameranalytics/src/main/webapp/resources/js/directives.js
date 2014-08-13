( function () {
	var appDirectives = angular.module("app.directives",[]);
	
	/**
	 * Directive for Trend Screen tabs
	 */
	appDirectives.directive('trendHeader', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'trendHeader'
	    };
	  });
	
	/**
	 * Directive for roaming trends
	 */
	appDirectives.directive('roamingTrend', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'roamingTrend'
	    };
	  });
})();