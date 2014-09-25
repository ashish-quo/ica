(function(){
	var trends = angular.module("app.trends",[]);
	
	/**
	 * Controller for trend actions
	 */
	var emptyChart = {
			title: {
                text: ''
            },
            credits: {
	            enabled: false
	        },
            subtitle: {
                text: ''
            },
            loading: true
	};
	function getChart(chartData, isDoW, logScale) {
		return  {
				options : {
			        chart: {
			        	type: isDoW == 'true' ? 'column' : "line"
		            },
		            tooltip: {
		            	shared: true,
		                useHTML: true,
		                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
		                footerFormat: '</table>'
		                	
		            }
				},
				credits: {
		            enabled: false
		        },
	            title: {
	                text: ''
	            },
	            subtitle: {
	                text: ''
	            },
		        xAxis: (function (){
		        	if (isDoW == 'true') {
		        		return {
			               categories :  chartData.dowCategoryList.map(function(value) {
	            			   		return value.substring(0,3);
		            	   		}) 
		        		}
		        	} else {
		        		return {
	        				type: 'datetime',
	        				labels: {
	        		            formatter: function() {
	        		                return Highcharts.dateFormat('%d %b', this.value);
	        		            }
	        		        }
		        		}
		        	}
        		})(),
	            yAxis: {
	            	type: logScale == 'true' ? 'logarithmic' : 'linear',
	                title: {
	                    text: ''
	                }
	            },
	            
		        series: isDoW == 'true' ? chartData.dowSeriesList : chartData.perDaySeriesList,
	            plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
		        loading: false
	    }
	};
	
	
	trends.controller('RoamingTrendController',
			['$scope','$rootScope','$http', 'util', function($scope,$rootScope,$http,util) {
				$scope.trends = {}; 
				$j('#roamer-ft-zoom').modalPopLite({ openButton: '.zoom-btn', closeButton: '.tre-close-btn', isModal: true });
				$scope.roamerCountChartConfig = emptyChart;
				$scope.roamerVoiceChartConfig = emptyChart;
				$scope.roamerDataChartConfig = emptyChart;
				$scope.roamerSMSChartConfig = emptyChart;
				
				$scope.dow = 'true';
				
				$scope.logScale = 'true';
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getRoamingTrendsData", data).success(function(result) {
					$scope.trends = result;
					$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
					$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.dow, 'false');
					$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dow, 'false');
					$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.dow, 'false');
				});
				
				$rootScope.$on('refresh-roaming-trends', function (event) {
					$scope.roamerCountChartConfig = emptyChart;
					$scope.roamerVoiceChartConfig = emptyChart;
					$scope.roamerDataChartConfig = emptyChart;
					$scope.roamerSMSChartConfig = emptyChart;
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					$http.get("getRoamingTrendsData", latestData).success(function(result) {
						$scope.trends = result;
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
						$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.dow, 'false');
						$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dow, 'false');
						$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.dow, 'false');
					});
				});
				
				$scope.$watch("dow", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
					}
					
					if ($scope.trends.roamerVoiceChartConfig) {
						$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.dow, 'false');
					}
					
					if ($scope.trends.roamerDataChartConfig) {
						$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dow, 'false');
					}
					
					if ($scope.trends.roamerSMSChartConfig) {
						$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.dow, 'false');
					}
					
				});
				
				$scope.$watch("logScale", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
					}
				});
				
				$scope.zoom = function(data, viewZoomScale) {
					$rootScope.zoomLogScale = $scope.logScale;
					$rootScope.viewZoomScale = viewZoomScale;
					dataCopy=JSON.parse(JSON.stringify(data));
					$rootScope.$broadcast("zoom-chart",dataCopy);
				};
	}]);
	
	
	trends.controller('ZoomController', ['$scope','$rootScope',function($scope,$rootScope) {
		$rootScope.$on('zoom-chart', function (event, param) {
			$scope.chartConfig = param;
		});
		$scope.changeLogScale = function(scale) {
			 $rootScope.zoomLogScale = scale;
		};
		$rootScope.$watch("zoomLogScale", function (newValue, oldValue) {
			if ($scope.chartConfig) {
				$scope.chartConfig.yAxis.type = $rootScope.zoomLogScale == 'true' ? 'logarithmic' : 'linear';
			}
		});
	}]);
	
	
	trends.controller('RoamingStatisticsControllerTrend',
			['$scope','$rootScope','$http','util',  function($scope,$rootScope,$http,util) {
				console.log("Call inside2");
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
				
				$rootScope.$on('refresh-roaming-statistics-trends', function (event) {
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
