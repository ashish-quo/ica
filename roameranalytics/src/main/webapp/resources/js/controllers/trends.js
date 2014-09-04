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
		// transform attribute filters
		var attrs = '';
		for (var key in filters.attributes) {
			  if (filters.attributes.hasOwnProperty(key)) {
			    attrs += '' + key + ':' + $j.map(filters.attributes[key], function(obj) {
			    	return obj.catId;
			    }).join(",")+ "#";
			  }
		}
		attrs = attrs.substring(0, attrs.length - 1);
		
		// transform persona filter
		var personas = $j.map(filters.personas, function(obj) {
	    	return obj.id;
	    }).join(",");
		
		// transform countries filter
		var countries = $j.map(filters.countries, function(obj) {
	    	return obj.id;
	    }).join(",");
		
		var tempAttrs = '';
		for (var key in filters.tempAttributes) {
			  if (filters.tempAttributes.hasOwnProperty(key)) {
				  tempAttrs += '' + key + ':' + filters.tempAttributes[key]+ "#";
			  }
		}
		
		
		tempAttrs = tempAttrs.substring(0, tempAttrs.length - 1);
		var params = { 'dateRangeFrom' : filters.dateRangeFrom,
			'dateRangeTo': filters.dateRangeTo,
			'attributes' : attrs,
			'countries' : countries,
			'personas': personas,
			'tempAttributes' : tempAttrs
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
				
				// local filter functions
				$scope.clearRoamingCategoryTempFilter = function () {
					delete $rootScope.filters.tempAttributes["2"];
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				$scope.clearARPUTempFilter = function () {
					delete $rootScope.filters.tempAttributes["3"];
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				$scope.clearPaymentTypeTempFilter = function () {
					delete $rootScope.filters.tempAttributes["4"];
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				$scope.getPrepaidCustomers = function() {
					$rootScope.filters.tempAttributes['4']='1';
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				
				$scope.getPostpaidCustomers = function() {
					$rootScope.filters.tempAttributes['4']='0'
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				
				$scope.getSilentCustomers = function() {
					$rootScope.filters.tempAttributes['2']='1'
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				
				$scope.getValueCustomers = function() {
					$rootScope.filters.tempAttributes['2']='2'
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				$scope.getPremiumCustomers = function() {
					$rootScope.filters.tempAttributes['2']='3'
					$rootScope.$broadcast("refresh-roaming-trends");
				};

				//ARPU filter functions
				$scope.getLowARPUCustomers = function() {
					$rootScope.filters.tempAttributes['3']='4'
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				
				$scope.getHighARPUCustomers = function() {
					$rootScope.filters.tempAttributes['3']='6'
					$rootScope.$broadcast("refresh-roaming-trends");
				};
				$scope.getMedARPUCustomers = function() {
					$rootScope.filters.tempAttributes['3']='5'
					$rootScope.$broadcast("refresh-roaming-trends");
				};
	}]);
	
})();