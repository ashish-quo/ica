(function(){
	var trends = angular.module("app.trends",['highcharts-ng']);
	
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
			['$scope','$rootScope','$http', 'util', 'httpService', 'pendingRequests', function($scope,$rootScope,$http,util,httpService, pendingRequests) {
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
				httpService.get($scope.roamType + "/getRoamingTrendsData", data).success(function(result) {
					$scope.trends = result;
					$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
					$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.dow, 'false');
					$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dow, 'false');
					$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.dow, 'false');
					if ($scope.roamerCountChartConfig.series.length == 0) {
						$scope.roamerCountChartConfig.subtitle.text = 'No data found'
					}
					if ($scope.roamerVoiceChartConfig.series.length == 0) {
						$scope.roamerVoiceChartConfig.subtitle.text = 'No data found'
					}
					if ($scope.roamerDataChartConfig.series.length == 0) {
						$scope.roamerDataChartConfig.subtitle.text = 'No data found'
					}
					if ($scope.roamerSMSChartConfig.series.length == 0) {
						$scope.roamerSMSChartConfig.subtitle.text = 'No data found'
					}
				});
				
				$rootScope.$on('refresh-roaming-trends', function (event) {
					$scope.roamerCountChartConfig = emptyChart;
					$scope.roamerVoiceChartConfig = emptyChart;
					$scope.roamerDataChartConfig = emptyChart;
					$scope.roamerSMSChartConfig = emptyChart;
					var latestData = {
						'params' : util.getParamsFromFilter($rootScope.filters)
					};
					httpService.get($scope.roamType + "/getRoamingTrendsData", latestData).success(function(result) {
						$scope.trends = result;
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
						$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.dow, 'false');
						$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dow, 'false');
						$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.dow, 'false');
						if ($scope.roamerCountChartConfig.series.length == 0) {
							$scope.roamerCountChartConfig.subtitle.text = 'No data found'
						}
						if ($scope.roamerVoiceChartConfig.series.length == 0) {
							$scope.roamerVoiceChartConfig.subtitle.text = 'No data found'
						}
						if ($scope.roamerDataChartConfig.series.length == 0) {
							$scope.roamerDataChartConfig.subtitle.text = 'No data found'
						}
						if ($scope.roamerSMSChartConfig.series.length == 0) {
							$scope.roamerSMSChartConfig.subtitle.text = 'No data found'
						}
					});
				});
				
				$scope.$watch("dow", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
						if ($scope.roamerCountChartConfig.series.length == 0) {
							$scope.roamerCountChartConfig.subtitle.text = 'No data found'
						}
					}
					
					if ($scope.trends.roamersCountChart) {
						$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.dow, 'false');
						if ($scope.roamerVoiceChartConfig.series.length == 0) {
							$scope.roamerVoiceChartConfig.subtitle.text = 'No data found'
						}
					}
					
					if ($scope.trends.roamersCountChart) {
						$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dow, 'false');
						if ($scope.roamerDataChartConfig.series.length == 0) {
							$scope.roamerDataChartConfig.subtitle.text = 'No data found'
						}
					}
					
					if ($scope.trends.roamersCountChart) {
						$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.dow, 'false');
						if ($scope.roamerSMSChartConfig.series.length == 0) {
							$scope.roamerSMSChartConfig.subtitle.text = 'No data found'
						}
					}
					
				});
				
				$scope.$watch("logScale", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.dow, $scope.logScale);
						if ($scope.roamerCountChartConfig.series.length == 0) {
							$scope.roamerCountChartConfig.subtitle.text = 'No data found'
						}
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
			['$scope','$rootScope','$http','util','httpService', 'pendingRequests',  function($scope,$rootScope,$http,util,httpService, pendingRequests) {
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
				httpService.get($scope.roamType + "/getRoamingStatistics", data).success(function(result) {
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
					setRoaminstatisticsFontSize();
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
					httpService.get($scope.roamType + "/getRoamingStatistics", latestData).success(function(result) {
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
					setRoaminstatisticsFontSize();
					
					
				});
				
				function setRoaminstatisticsFontSize()
				{
					/* Added by Smruti for roamernalytics text size issue */
					if($scope.totalRoamer.toString().length > 4){
						$j($j("div.dashboard-statics")[0]).find("p.lightblue-text").addClass("smallsize");
					}
					else{
						var roamersElement = $j($j("div.dashboard-statics")[0]).find("p.lightblue-text");
						if(roamersElement.hasClass("smallsize")) {
							roamersElement.removeClass("smallsize");
						}
					}					
					if($scope.totalMo.toString().length > 4){
						$j($j("div.dashboard-statics")[0]).find("p.purple-text").addClass("smallsize");
					}					
					else{
						var roamersElement = $j($j("div.dashboard-statics")[0]).find("p.purple-text");
						if(roamersElement.hasClass("smallsize")) {
							roamersElement.removeClass("smallsize");
						}
					}
					if($scope.totalMt.toString().length > 6){
						$j($j("div.dashboard-statics")[1]).find("p.green-text").addClass("smallsizemiddle");
					}					
					else{
						var roamersElement = $j($j("div.dashboard-statics")[1]).find("p.green-text");
						if(roamersElement.hasClass("smallsizemiddle")) {
							roamersElement.removeClass("smallsizemiddle");
						}
					}
					if($scope.totalData.toString().length > 6){
						$j($j("div.dashboard-statics")[1]).find("p.orange-text").addClass("smallsizemiddle");
					}					
					else{
						var roamersElement = $j($j("div.dashboard-statics")[1]).find("p.orange-text");
						if(roamersElement.hasClass("smallsizemiddle")) {
							roamersElement.removeClass("smallsizemiddle");
						}
					}
					if($scope.totalSms.toString().length > 6){
						$j($j("div.dashboard-statics")[1]).find("p.yellow-text").addClass("smallsizemiddle");
					}					
					else{
						var roamersElement = $j($j("div.dashboard-statics")[1]).find("p.yellow-text");
						if(roamersElement.hasClass("smallsizemiddle")) {
							roamersElement.removeClass("smallsizemiddle");
						}
					}
				}
				

				
		
	}]);
	
})();
