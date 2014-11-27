(function() {
	var msDirective = angular.module("app.msdirective", []);
	
	
	msDirective.service('msChartService', ['$http', '$rootScope','util', function($http,$rootScope, util) {
    	  
    	  this.drawMorrisChart = function(element, data,chartname,width) {
    		  var c3Columns = data;
    		  
    		  // for more than 3 chart will be distorted
    		  if (c3Columns.length > 10) {
    			  c3Columns = c3Columns.slice(0,3);
    		  }
    		  if (c3Columns.length > 0) {
	    		  c3.generate({
	    			  bindto: '#'+element.attr('id'),
	    	        data: {
	    	          columns: c3Columns,
	    	          type : 'donut',
	    	          onclick: function (d, i) {
	    	        	  
	    	        	  if (d.id == 'Unknown' || d.id == 'null' || d.id == 'NULL' || chartname == 'Other Countries Traveled') 
	    	        		  return;
	    	        	  var target = $j( "input[name='" + d.id + "']")[0];
	    	        	  var filterId = $j(target).attr("id").split('_');
	    	        	  if (filterId[0] == 'country') {
	    	        		  target = $j( "input[name='" + d.id + "']")[1];
	    	        		  filterId = $j(target).attr("id").split('_');
	    	        	  }
	    	        	  $j(target).attr('checked', 'checked');
	    	        	  var filter = {'attributeId':filterId[0],'categoryId':filterId[1]};
	    	        	  $rootScope.$broadcast('add-filter-from-microsegment',filter);
	    	          },
	    	        },
	    	        color: {
	    	            pattern: ['#fbe591', '#88d4cd', '#fa9092', '#f59dbe', '#beb6ee']
	    	        },
	    	        donut: {
	    	          label: {
    	                format: function (d, ratio) { return ""; }
    	              },
	    	          title: "",
	    	          width:width
	    	        },
	    	        tooltip: {
	    	            format: {
	    	                value: function (value, ratio, id) {
	    	                    var format = d3.format(',');
	    	                    return d3.round(value,1);
	    	                }
	    	            }
	    	        }
	    	      });
    		  }
    	  };
    	  
    	  // draws column chart
    	  this.drawVerticalBarChart = function (element, data) {
    		  chart2 =  element.highcharts(util.getMSColumnChart(data.map(function(obj) {
				  return obj[0];
			  }), data.map(function(obj) {
				  return obj[1];
			  })));
    	  };
    	  
    	  // draws bar chart
    	  this.drawHorizontalBarChart = function (element, data) {
    		  chart1 = element.highcharts(util.getMSBarChart(data.map(function(obj) {
				  return obj[0];
			  }), data.map(function(obj) {
				  return obj[1];
			  })));
    	  };
    	  // refreshes chart when microsegment measure is changed
    	  this.changeAttributeMeasure = function(data, measure, attrs,element) {
    		  var dataToPlot = data[measure];
    		  var verticalChart = $j('#column-chart-'+attrs.chartname.replace(/ /g,''));
    		  var horizontalChart = $j('#bar-chart-'+attrs.chartname.replace(/ /g,''));
    		  verticalChart.html('');
    		  horizontalChart.html('');
    		  element.html('');
    		  element.removeClass("no-data-found");
    		  if (dataToPlot != null && dataToPlot.length > 0) {
	    		  if (attrs.charttype == '1') {
	    			  var donutDataAvailable = false;
		    		  for (var i = 0; i < dataToPlot.length;i++) {
		    			  if (dataToPlot[i][1] != 0) {
		    				  donutDataAvailable = true;
		    				  break;
		    			  }
		    		  }
		    		  if (donutDataAvailable)
		    			  this.drawMorrisChart(element,dataToPlot, attrs.chartname,50);	
		    		  else 
		    			  element.addClass("no-data-found");
		    		  
	    		  } else if (attrs.charttype == '2') {
	    			  var donutData ;
		    		  var columnData;
		    		  columnData = dataToPlot.slice(0,2);
		    		  donutData = dataToPlot.slice(2);
		    		  if (donutData.length >= 3) {
		    			  donutData = donutData.slice(0,3);
		    		  }
		    		  var columnDataAvailable = false;
		    		  for (var i = 0; i < columnData.length;i++) {
		    			  if (columnData[i][1] != 0) {
		    				  columnDataAvailable = true;
		    				  break;
		    			  }
		    		  }
		    		  var donutDataAvailable = false;
		    		  for (var i = 0; i < donutData.length;i++) {
		    			  if (donutData[i][1] != 0) {
		    				  donutDataAvailable = true;
		    				  break;
		    			  }
		    		  }
		    		  
		    		  element.removeClass("big-donutchart").addClass("medium-donutchart")
		    		  if (columnDataAvailable)
		    			  this.drawHorizontalBarChart(horizontalChart,columnData);
		    		  if (donutDataAvailable)
		    			  this.drawMorrisChart(element,donutData,25);
		    		  if (!donutDataAvailable && !columnDataAvailable) {
		    			  element.addClass("no-data-found");
		    		  }
	    		  } else if (attrs.charttype == '3') {
		    		  var donutData ;
		    		  var columnData;
	    			  donutData = dataToPlot.slice(0,3);
	    			  columnData = dataToPlot.slice(3);
	    			  
	    			  var columnDataAvailable = false;
		    		  for (var i = 0; i < columnData.length;i++) {
		    			  if (columnData[i][1] != 0) {
		    				  columnDataAvailable = true;
		    				  break;
		    			  }
		    		  }
		    		  var donutDataAvailable = false;
		    		  for (var i = 0; i < donutData.length;i++) {
		    			  if (donutData[i][1] != 0) {
		    				  donutDataAvailable = true;
		    				  break;
		    			  }
		    		  }
		    		  
		    		  if (columnDataAvailable) {
		    			  if (columnData.length > 0) {
		    				  element.removeClass("big-donutchart").addClass("medium-donutchart");
		    				  this.drawVerticalBarChart(verticalChart,columnData);
		    			  }
		    		  }
		    		  if (donutDataAvailable)
		    			  this.drawMorrisChart(element,donutData,25);
		    		  if (!donutDataAvailable && !columnDataAvailable) {
		    			  element.addClass("no-data-found");
		    		  }
		    		  
	    		  }
    		  } else {
    			  element.addClass("no-data-found");
    		  }
    	  };
	}]);

	/**
	 * Directive for HeatMap
	 */
	msDirective.directive('donutchart', ['$rootScope','$http',  'util', 'msChartService','httpService',
	                                     'httpNoDataService', 'pendingRequests',  function($rootScope,$http,util,msChartService,httpService,
	    	                                     httpNoDataService, pendingRequests) {
	    return {
	      restrict: 'E',
	      template: "<div class='donut loading'></div>",
	      replace: true,
	        // observe and manipulate the DOM
	      link: function($scope, element, attrs) {
	    	  $scope.title[attrs.chartname] = attrs.chartname;
	    	  // gets data initially and draws all charts
	    	  var getDataAndDraw = function()  {
	    		  var data = {
		    			  'params' : util.getParamsFromFilter($rootScope.filters)
		    	  };
	    		  var chartMetaData = attrs.chartname + "," + attrs.columnname +  "," + attrs.columntype + "," + attrs.charttype;
	    		  $j.extend(data.params, {'chartmetadata' :chartMetaData } );
	    		  
	    		  var url =  "/";
	    		  if (attrs.chartname == 'Network Group') {
	    			  url+= 'microsegment/networkgroup/'
	    		  } else  if (attrs.chartname == 'Network') {
	    			  url+= 'microsegment/network/'
	    		  } else if (attrs.chartname == 'Other Countries Traveled'){
	    			  url+= 'microsegment/otherCountriesTraveled';
	    		  } else {
    				  url+= 'microsegment/graph/';
	    		  }
	    		  element.html('');
	    		  var verticalChart = $j('#column-chart-'+attrs.chartname.replace(/ /g,''));
	    		  var horizontalChart = $j('#bar-chart-'+attrs.chartname.replace(/ /g,''));
	    		  verticalChart.html('');
	    		  horizontalChart.html('');
	    		  element.removeClass("no-data-found");
	    		  element.addClass("loading");
	    		  httpService.get($scope.roamType + url , data).success(function(result) {
		    		  $scope.msdata = result.data;
		    		  $scope.title[attrs.chartname] = result.attrName;
		    		  element.removeClass("no-data-found");
		    		  msChartService.changeAttributeMeasure(result.data,$rootScope.attributemeasure, attrs, element);
		    		  element.removeClass("loading");
		    		  $rootScope.mschartcount = $rootScope.mschartcount + 1;
		    		  if ($rootScope.mschartcount == $scope.loadingCount) {
		    			  $rootScope.showmore[$rootScope.showmoreindex++] = true;
		    			  $rootScope.mschartcount = 0;
		    		  }
		    	  }).error(function(data, status, headers, config) {
		    		  element.removeClass("no-data-found");
		    		  element.removeClass("loading");
		    		  element.addClass("internal-error");
		    		  $rootScope.mschartcount = $rootScope.mschartcount + 1;
		    		  if ($rootScope.mschartcount == $scope.loadingCount) {
		    			  $rootScope.showmore[$rootScope.showmoreindex++] = true;
		    			  $rootScope.mschartcount = 0;
		    		  }
		    	  });
	    	  };
	    	  
	    	  // get data and draw chart
	    	  getDataAndDraw();
	    	  
	    	  $scope.$watch('microsegmentdaterange', function(oldValue, newValue) {
	    		  if (oldValue != newValue) {
	    			  $rootScope.mschartcount = 0;
	    			  $rootScope.showmore = util.booleanArray($rootScope.numberOfCharts%$scope.loadingCount == 0 
	    					  ? $rootScope.numberOfCharts/$scope.loadingCount:
	    				  ($rootScope.numberOfCharts/$scope.loadingCount + 1));
	    			  getDataAndDraw();
	    		  } 
	    	  },true);
	    	  
	    	  $scope.$watch('microsegmentrefresh', function(oldValue, newValue) {
	    		  if (oldValue != newValue && oldValue != null) {
	    			  $rootScope.mschartcount = 0;
	    			  $rootScope.showmore = util.booleanArray($rootScope.numberOfCharts%$scope.loadingCount == 0 
	    					  ? $rootScope.numberOfCharts/$scope.loadingCount:
	    				  ($rootScope.numberOfCharts/$scope.loadingCount + 1));
	    			  getDataAndDraw();
	    		  } 
	    	  },true);
	    	  
	    	  $rootScope.$watch("attributemeasure", function (newValue) {
	    		  if (newValue != null && $scope.msdata != null && $scope.msdata[newValue] != null) {
	    			  msChartService.changeAttributeMeasure($scope.msdata,newValue, attrs, element);
	    		  }
	  		});
	      }
	    };
	  }]);

})();