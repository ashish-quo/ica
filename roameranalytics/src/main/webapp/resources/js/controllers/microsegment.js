(function() {
	var microsegment = angular.module("app.microsegment",[]);
	microsegment.controller('MicroSegmentController', [ '$scope', '$rootScope',
			'$http', 'util', function($scope, $rootScope, $http, util) {
		
		$scope.microsegmentrefresh = true;
		$rootScope.attributemeasure = 'roamers';
		$rootScope.microsegmentSetting = 'roamers';
		$rootScope.changeMSAttributeMeasure =  function (value) {
			$rootScope.microsegmentSetting = value;
		};
		$rootScope.applyMicrosegmentSettings = function() {
			$j('span.dropdown.micro-setting-area').removeClass('open');
			$rootScope.attributemeasure = $rootScope.microsegmentSetting;
		}
		
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
					var chartType = $j(element[0]).attr("chart-type");
					var elementName = $j(element[0]).attr("attr-name");
					var attributeId = $j(element[0]).attr("attr-id");
					cahrtArray.push(elementName + "," + columnName + "," + columnType + "," + chartType + "," + attributeId);
				}
			});
			data.params.microsegmentcharts = cahrtArray.join(":");	
			$http.get("microsegment/graphs", data).success(function(result) {
				$scope.graphToBeShown = result;
				$scope.graphToBeShown = $scope.graphToBeShown.map(function (obj) {
					obj.id = obj.title.replace(/ /g,'');
					return obj;
				});
			}).error(function(data, status, headers, config) {
		        $rootScope.error = 'Internal server error';
		    });
		};
		
		getMircosegmentCharts();
		
		$rootScope.$on("refresh-microsegment-daterange", function(event) {
			$scope.microsegmentrefresh = !$scope.microsegmentrefresh ;
		});
		
		$rootScope.$on("refresh-microsegment-country", function(event) {
			getMircosegmentCharts();
		});
		
		$rootScope.$on("refresh-microsegment-attribute", function(event) {
			getMircosegmentCharts();
		});
		
	} ]);
	
	/** Added By smruti for roamingStatistics 
	 * 
	 */
	microsegment.controller('RoamingStatisticsControllerMicrosegment',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
		
				$scope.totalRoamer = 0;
				$scope.silentRoamer = 0;
				$scope.valueRoamer = 0;
				$scope.premiumRoamer = 0;
				
				$scope.totalMo = 0;
				$scope.homeMo=0;
				$scope.localMo = 0;
				$scope.intlMo=0;
				
				$scope.totalMt = 0;
				$scope.totalData=0;
				$scope.totalSms=0;
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getRoamingStatistics", data).success(function(result) {
					$scope.roamingStatistics = result;
					$scope.totalRoamer = result.totalRoamer;
					$scope.silentRoamer = result.silentRoamer;
					$scope.valueRoamer = result.valueRoamer;
					$scope.premiumRoamer = result.premiumRoamer;
					
					$scope.totalMo = result.totalMo;
					$scope.homeMo=result.homeMo;
					$scope.localMo = result.localMo;
					$scope.intlMo=result.intlMo;
					
					$scope.totalMt = result.totalMt;
					$scope.totalData=result.totalData;
					$scope.totalSms=result.totalSms;
				});
				
				$rootScope.$on('refresh-roaming-statistics-microsegment', function (event) {
					$scope.totalRoamer = 0;
					$scope.silentRoamer = 0;
					$scope.valueRoamer = 0;
					$scope.premiumRoamer = 0;
					
					$scope.totalMo = 0;
					$scope.homeMo=0;
					$scope.localMo = 0;
					$scope.intlMo=0;
					
					$scope.totalMt = 0;
					$scope.totalData=0;
					$scope.totalSms=0;
					
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getRoamingStatistics", latestData).success(function(result) {
						$scope.roamingStatistics = result;
						$scope.totalRoamer = result.totalRoamer;
						$scope.silentRoamer = result.silentRoamer;
						$scope.valueRoamer = result.valueRoamer;
						$scope.premiumRoamer = result.premiumRoamer;
						
						$scope.totalMo = result.totalMo;
						$scope.homeMo=result.homeMo;
						$scope.localMo = result.localMo;
						$scope.intlMo=result.intlMo;
						
						$scope.totalMt = result.totalMt;
						$scope.totalData=result.totalData;
						$scope.totalSms=result.totalSms;
					});
				});
				
						
				
		
	}]);

})();
