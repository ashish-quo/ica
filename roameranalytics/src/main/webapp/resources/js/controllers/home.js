(function(){
	var homeC = angular.module("app.home",[]);
	
	
	homeC.controller('RoamingStatisticsController',
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
				
				$rootScope.$on('refresh-roaming-statistics', function (event) {
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
	
	homeC.controller('HeatMapController',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
		
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getHeatMap", data).success(function(result) {
					
				});
				
				$rootScope.$on('refresh-heatmap-home', function (event) {
					
					
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getHeatMap", latestData).success(function(result) {
						
					});
				});
				
						
				
		
	}]);
	
	
	homeC.controller('BubbleChartController',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
		
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getBubbleChart", data).success(function(result) {
					
				});
				
				$rootScope.$on('refresh-bubblechart-home', function (event) {
					
					
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getBubbleChart", latestData).success(function(result) {
						
					});
				});
				
						
				
		
	}]);
	
})();