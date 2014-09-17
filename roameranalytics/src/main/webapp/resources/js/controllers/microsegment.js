(function() {
	var microsegment = angular.module("app.microsegment",[]);
	microsegment.controller('MicroSegmentController', [ '$scope', '$rootScope',
			'$http', 'util', function($scope, $rootScope, $http, util) {
		
		function getMircosegmentCharts() {
			$scope.graphToBeShown = {};
			$scope.title = {};
			var data = {
					'params' : util.getParamsFromFilter($rootScope.filters)
			};
			
			var cahrtArray = new Array();
			var selectAll = $j("input.all-attr");
			var parent = $j(selectAll).closest('form');
			parent.each(function () {
				if($j(this).find('.sub-check').length == $j(this).find(".sub-check:not(:checked)").length
						|| $j(this).find('.sub-check').length == $j(this).find(".sub-check:checked").length) {
					var element = $j(this).find("input.all-attr");
					var columnName = $j(element[0]).attr("db-column");
					var columnType = $j(element[0]).attr("column-type");
					var elementName = $j(element[0]).attr("attr-name");
					cahrtArray.push(elementName + "," + columnName + "," + columnType);
				}
			});
			data.params.microsegmentcharts = cahrtArray.join(":");	
			$http.get("microsegment/graphs", data).success(function(result) {
				$scope.graphToBeShown = result;
			}).error(function(data, status, headers, config) {
		        //$scope.$parent.error = data.message;
		    });
		};
		
		getMircosegmentCharts();
		$rootScope.$on("refresh-microsegment-daterange", function(event, daterange) {
			if (daterange != null ) {
				$scope.microsegmentdaterange = daterange;
			} else {
				getMircosegmentCharts();
			}
		});
		$scope.microsegmentrefresh = true;
		$rootScope.$on("refresh-microsegment-country", function(event) {
			$scope.microsegmentrefresh = !$scope.microsegmentrefresh ;
		});
		
		$rootScope.$on("refresh-microsegment-attribute", function(event) {
			getMircosegmentCharts();
		});
		
	} ]);

})();
