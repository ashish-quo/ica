(function(){
	var trends = angular.module("app.trends",[]);
	
	/**
	 * Controller for trend actions
	 */
	var emptyChart = {
			title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            loading: true
	};
	function getChart(chartData, isDoW, logScale) {
		return {
				options : {
			        chart: {
			        	type: isDoW == 'true' ? 'column' : "line"
		            }
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
	        		                return Highcharts.dateFormat('%d-%m-%y', this.value);
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
	            
	            tooltip: {
	                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                    '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	                footerFormat: '</table>',
	                shared: true,
	                useHTML: true	
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
	
	function getParamsFromFilter(filters) {
		var attrs = '';
		for (var key in filters.attributes) {
			  if (filters.attributes.hasOwnProperty(key)) {
			    attrs += '' + key + ':' + filters.attributes[key].join(",")+ "#";
			  }
		}
		attrs = attrs.substring(0, attrs.length - 1);
		var params = { 'dateRangeFrom' : filters.dateRangeFrom,
			'dateRangeTo': filters.dateRangeTo,
			'attributes' : attrs,
			'countries' : filters.countries.join(",")
		}
		return params;
	};
	trends.controller('RoamingTrendController',
			['$scope','$rootScope','$http', 'util', function($scope,$rootScope,$http,util) {
				$scope.trends = {}; 
				
				$scope.roamerCountChartConfig = emptyChart;
				$scope.roamerVoiceChartConfig = emptyChart;
				$scope.roamerDataChartConfig = emptyChart;
				$scope.roamerSMSChartConfig = emptyChart;
				
				$scope.countDoW = 'true';
				$scope.voiceDoW = 'true';
				$scope.dataDoW = 'true';
				$scope.smsDoW = 'true';
				$scope.logScale = 'true';
				var data = {
						'params' : getParamsFromFilter($rootScope.filters)
				};
				$http.get("getRoamingTrendsData", data).success(function(result) {
					$scope.trends = result;
					$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.countDoW, $scope.logScale);
					$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.voiceDoW, 'false');
					$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dataDoW, 'false');
					$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.smsDoW, 'false');
				});
				
				$rootScope.$on('refresh-roaming-trends', function (event) {
					$scope.roamerCountChartConfig = emptyChart;
					$scope.roamerVoiceChartConfig = emptyChart;
					$scope.roamerDataChartConfig = emptyChart;
					$scope.roamerSMSChartConfig = emptyChart;
					var latestData = {
						'params' : getParamsFromFilter($rootScope.filters)
					};
					$http.get("getRoamingTrendsData", latestData).success(function(result) {
						$scope.trends = result;
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.countDoW, $scope.logScale);
						$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.voiceDoW, 'false');
						$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dataDoW, 'false');
						$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.smsDoW, 'false');
					});
				});
				
				$scope.$watch("countDoW", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.countDoW, $scope.logScale);
					}
				});
				$scope.$watch("voiceDoW", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerVoiceChartConfig = getChart($scope.trends.roamersMTMOChart, $scope.voiceDoW, 'false');
					}
				});
				$scope.$watch("dataDoW", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerDataChartConfig = getChart($scope.trends.roamersDataChart, $scope.dataDoW, 'false');
					}
				});
				$scope.$watch("smsDoW", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerSMSChartConfig = getChart($scope.trends.roamersSMSChart, $scope.smsDoW, 'false');
					}
				});
				
				$scope.$watch("logScale", function (newValue, oldValue) {
					if ($scope.trends.roamersCountChart) {
						$scope.roamerCountChartConfig = getChart($scope.trends.roamersCountChart, $scope.countDoW, $scope.logScale);
					}
				});
	}]);
	
	trends.controller('TrendController',
			['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		$rootScope.trendTabIndex = 0;
		$scope.refresh = {
				'value' : 'true'
		};
		$scope.showHeatMap = function() {
			$rootScope.trendTabIndex = 0;
		};
		$scope.showTopTen = function() {
			$rootScope.trendTabIndex = 1;
		};
		$scope.showRoamingTrend = function() {
			$rootScope.trendTabIndex = 2;
		};

		$scope.isHeatMapSelected = function() {
			return $rootScope.trendTabIndex == 0;
		};
		$scope.isTopTenSelected = function() {
			return $rootScope.trendTabIndex == 1;
		};
		$scope.isRoamingTrendSelected = function() {
			return $rootScope.trendTabIndex == 2;
		};
		$scope.$on('refresh', function(event, args) {
			if ($scope.refresh == 'true') {
				$scope.refresh = 'false';
			} else {
				$scope.refresh = 'true';
			}
			
		});
	}]);
})();