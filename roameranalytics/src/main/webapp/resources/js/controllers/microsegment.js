(function() {

	var microsegment = angular.module("app.microsegment",[]);
	microsegment.controller('MicroSegmentController', [ '$scope', '$rootScope',
			'$http', 'util', function($scope, $rootScope, $http, util) {
			
		$scope.graphToBeShown = {};
		$scope.title = {};
		var data = {
				'params' : util.getParamsFromFilter($rootScope.filters)
		};
		$http.get("microsegment/graphs", data).success(function(result) {
			$scope.graphToBeShown = result;
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		$rootScope.$on("refresh-microsegment", function(event, daterange) {
			if (daterange != null) {
				$scope.microsegmentdaterange = daterange;
				
			} else {
				$scope.graphToBeShown = {};
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("microsegment/graphs", data).success(function(result) {
					$scope.graphToBeShown = result;
				}).error(function(data, status, headers, config) {
			        //$scope.$parent.error = data.message;
			    });
			}
		});
		
	} ]);

})();
