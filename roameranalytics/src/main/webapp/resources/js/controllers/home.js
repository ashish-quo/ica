(function(){
	var homeC = angular.module("app.home",[]);
	
		var roamerJsonMap=[];	
		var moJsonMap=[];
		var mtJsonMap=[];
		var dataJsonMap=[];
		var top10RoamerJson=[]
		var top10MoJson=[]
		var top10MtJson=[]
		var top10DataJson=[]
		var top10SmsJson=[]
		
		var colorAxisText=[ {
            dataClasses: [{
                to: 30,
				name: 'Positive'
            }, {
                from: 30,
                to: 65,
				name: 'Expected' 
            }, {
                from: 65,
                to: 100 ,
				name: 'Negative'
            }],
			minColor: '#87d796',
            maxColor: '#fca5ad'

        }];
		
		var colorAxisRange=[ {
            dataClasses: [{
                to: 3
            }, {
                from: 3,
                to: 10
            }, {
                from: 10,
                to: 30
            }, {
                from: 30,
                to: 100
            },{
                from: 100
            }],
				

        }];
		
	
	   
	   // Initiate the chart
       function initiateMap(data,colorAxisJson,suffixLable,nameLable)
       {
       $j('#map-container').highcharts('Map', {
           chart : {
               borderWidth : 0
           },
           title : {
               text : ''
           },

           mapNavigation: {
               enabled: true
           },
           
           legend: {
               title: {
                   text: '',
                   style: {
                       color: (Highcharts.theme && Highcharts.theme.textColor) || 'Black'
                   }
               },
               align: 'left',
				borderWidth:1,
               verticalAlign: 'bottom',
               floating: true,
               layout: 'vertical',
               valueDecimals: 0,
               backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || 'rgba(255,255,255,0.6)',
               symbolRadius: 0,
               symbolHeight: 14,
				borderColor:'#d9dce3'
           },

           colorAxis: colorAxisJson,
	
			
           series : [{
               data : data,
               mapData: Highcharts.maps['custom/world'],
		joinBy: ['name', 'countryName'],
                   name: nameLable,
                   states: {
                       hover: {
                           color: '#BADA55'
                       }
                   },
                   dataLabels: {
                       enabled: false, /*to disable showing lebles*/
                       format: '{point.name}'
                   },
               tooltip: {
                   valueSuffix: suffixLable
               }
           }]
       });
console.log("Inside mapcore");
       }
       
       function intiateTop10Bubble(){

       }
       
       
       function   intiateTop10Bar(){
    	   
    	   

       }
	
	homeC.controller('RoamingStatisticsControllerHome',
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
				console.log("datamap"+$scope.totalRoamer);
				intiateTop10Bubble();
				intiateTop10Bar();
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
					console.log("Call inside1");
				});
				
				
				$rootScope.$on('refresh-roaming-statistics', function (event) {
					
					console.log($rootScope.tabIndex);
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
					console.log("Call inside2");
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
						console.log("Call inside3");
					});
					
				});
				
				
				
				
						
				
		
	}]);
	
	
	
	
	homeC.controller('HeatMapControllerHome',
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
			
				
				function setHeatMapJson(result)
				{
					roamerJsonMap=[];	
					moJsonMap=[];
					mtJsonMap=[];
					dataJsonMap=[];
					
					var sortedRoamer =[];
					 var sortedMo=[];
					 var sortedMt =[];
					 var sortedData =[];
					 var sortedSms =[];
					
					angular.forEach(result, function(countryData) {
						
						roamerJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.roamerTotal
					    });
						
						moJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.moTotal
					    });
						
						mtJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.mt
					    });
						
						dataJsonMap.push({
					        'countryName': countryData.countryCode,
					        value: countryData.dataUsage
					    });
						
						sortedRoamer.push(countryData);
						sortedMo.push(countryData);
						sortedMt .push(countryData);
						sortedData.push(countryData);
						sortedSms .push(countryData);
						
						$scope.totalRoamer = $scope.totalRoamer + parseInt(countryData.roamerTotal, 10);
						$scope.silentRoamer = $scope.silentRoamer + parseInt(countryData.roamerSilent, 10);
						$scope.valueRoamer = $scope.valueRoamer + parseInt(countryData.roamerValue, 10);
						$scope.premiumRoamer = $scope.premiumRoamer + parseInt(countryData.roamerPremium, 10);
						
						$scope.totalMo = $scope.totalMo + parseInt(countryData.moTotal, 10);
						$scope.homeMo=$scope.homeMo + parseInt(countryData.moHome, 10);
						$scope.localMo = $scope.localMo + parseInt(countryData.moLocal, 10);
						$scope.intlMo=$scope.intlMo + parseInt(countryData.moIntl, 10);
						
						$scope.totalMt = $scope.totalMt + parseInt(countryData.mt, 10);
						$scope.totalData=$scope.totalData + parseInt(countryData.dataUsage, 10);
						$scope.totalSms=$scope.totalSms + parseInt(countryData.smsUsage, 10);
						
					});
					
					 
					 
					 sortedRoamer.sort(function(a, b){
					        return parseInt(b['roamerTotal'],10) - parseInt(a['roamerTotal'],10);
					    });
					 
					 sortedMt.sort(function(a, b){
						 return parseInt(b['mtTotal'],10) - parseInt(a['mtTotal'],10);
					    });
					 sortedMo.sort(function(a, b){
						 return parseInt(b['moTotal'],10) - parseInt(a['moTotal'],10);
					    });
					 sortedData.sort(function(a, b){
						 return parseInt(b['dataUsage'],10) - parseInt(a['dataUsage'],10);
					    });
					 sortedSms.sort(function(a, b){
						 return parseInt(b['smsUsage'],10) - parseInt(a['smsUsage'],10);
					    });
					
					 	top10RoamerJson=JSON.stringify(sortedRoamer.slice(0, 10));
						top10MoJson=JSON.stringify(sortedMo.slice(0, 10));
						top10MtJson=JSON.stringify(sortedMt.slice(0, 10));
						top10DataJson=JSON.stringify(sortedData.slice(0, 10));
						top10SmsJson=JSON.stringify(sortedSms.slice(0, 10));
						
						sortedRoamer =[];
						 sortedMo=[];
						sortedMt =[];
						 sortedData =[];
						 sortedSms =[];
					 
			}
				
				var data = {
						'params' : util.getParamsFromFilter($rootScope.filters)
				};
				$http.get("getHeatMap", data).success(function(result) {
							
					setHeatMapJson(result);
					initiateMap(roamerJsonMap,colorAxisRange,'','Roamer count');
					
				});
				
				$rootScope.$on('refresh-heatmap-home', function (event) {
					
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
					$http.get("getHeatMap", latestData).success(function(result) {
						
						setHeatMapJson(result);
						if ($scope.mapUnit=='roamers') {
							initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
						}else if ($scope.mapUnit=='mt') {
							initiateMap(mtJsonMap,colorAxisRange,'','MT Count');
						}else if ($scope.mapUnit=='mo') {
							initiateMap(moJsonMap,colorAxisRange,'','MO Count');
						}else if ($scope.mapUnit=='data') {
							initiateMap(dataJsonMap,colorAxisRange,' MB','Data Usage');
						}else{
							initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
						}
					});
				});
				
				$scope.$watch("mapUnit", function (newValue, oldValue) {
					if ($scope.mapUnit=='roamers') {
						initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
					}else if ($scope.mapUnit=='mt') {
						initiateMap(mtJsonMap,colorAxisRange,'','MT Count');
					}else if ($scope.mapUnit=='mo') {
						initiateMap(moJsonMap,colorAxisRange,'','MO Count');
					}else if ($scope.mapUnit=='data') {
						initiateMap(dataJsonMap,colorAxisRange,' MB','Data Usage');
					}else{
						initiateMap(roamerJsonMap,colorAxisRange,'','Roamer Count');
					}
					
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