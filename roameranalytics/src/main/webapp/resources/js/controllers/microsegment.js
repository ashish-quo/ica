(function() {
	var microsegment = angular.module("app.microsegment",[]);
	microsegment.controller('MicroSegmentController', [ '$scope', '$rootScope',
			'$http', 'util','httpService', 'pendingRequests',  function($scope, $rootScope, $http, util,httpService, pendingRequests ) {
		
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
			$scope.graphToBeShown = new Array();
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
			httpService.get($scope.roamType + "/microsegment/graphs", data).success(function(result) {
				var charts = result.charts;
				$scope.loadingCount = parseInt(result.loadingCount,10);
				charts = charts.map(function (obj) {
					obj.id = obj.title.replace(/ /g,'');
					return obj;
				});
				$scope.graphToBeShown = util.sliceList(charts,$scope.loadingCount);
				$rootScope.mschartcount = 0;
				$rootScope.showmore = util.booleanArray(parseInt(charts.length/$scope.loadingCount, 10) + 1);
				$rootScope.numberOfCharts = charts.length;
				$rootScope.showmoreindex = 1;
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
				
				
				$j(".value").addClass("donut").addClass("loading-right");
				$j(".dashboard-scroll").niceScroll();
				httpService.get($scope.roamType  + "/getRoamingStatistics", data).success(function(result) {
					console.log('first')
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
					$j(".value").removeClass("donut").removeClass("loading-right");
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
					$j(".value").addClass("donut").addClass("loading-right");
					httpService.get($scope.roamType + "/getRoamingStatistics", latestData).success(function(result) {
						console.log('refresh-roaming-statistics-microsegment')
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
						$j(".value").removeClass("donut").removeClass("loading-right");
					});
					
					
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
