( function () {
	var appDirectives = angular.module("app.directives",[]);
	
	appDirectives.directive('trendHeader', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'trendHeader'
	    };
	  });
})();