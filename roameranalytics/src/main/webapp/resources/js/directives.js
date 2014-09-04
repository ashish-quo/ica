( function () {
	var appDirectives = angular.module("app.directives",["highcharts-ng"]);
	

	
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
	 * Directive for HeatMap
	 */
	appDirectives.directive('heatMap', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'heatMap',
	    };
	  });
})();