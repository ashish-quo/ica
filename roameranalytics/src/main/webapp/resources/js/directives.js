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
	/**
	 * Directive for roaming trends
	 */
	appDirectives.directive('a', function() {
	    return {
	      restrict: 'E',
	      link: function(scope,element,attr) {
	    	  element.on("click",function(event){
	    		  
	    	  });
	      }
	    };
	  });
})();