(function() {
	var msDirective = angular.module("app.msdirective", []);
	
	
	msDirective.service('msChartService', ['$http', '$rootScope','util', function($http,$rootScope, util) {
    	  
    	  this.drawMorrisChart = function(element, data) {
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
	    	        donut: {
	    	          title: "",
	    	          width:50
	    	        },
	    	        tooltip: {
	    	            format: {
	    	                value: function (value, ratio, id) {
	    	                    var format = d3.format(',');
	    	                    return format(value);
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
    		  
    		  if (dataToPlot != null && dataToPlot.length > 0) {
    			 
    			  element.removeClass("no-data-found")
	    		  if (attrs.charttype == '1') {
	    			  this.drawMorrisChart(element,dataToPlot);
	    		  } else if (attrs.charttype == '2') {
	    			  var donutData ;
		    		  var columnData;
		    		  columnData = dataToPlot.slice(0,2);
		    		  donutData = dataToPlot.slice(3);
		    		  element.removeClass("big-donutchart").addClass("medium-donutchart")
		    		  this.drawHorizontalBarChart(horizontalChart,columnData);
		    		  this.drawMorrisChart(element,donutData);
	    		  } else if (attrs.charttype == '3') {
		    		  var donutData ;
		    		  var columnData;
	    			  donutData = dataToPlot.slice(0,3);
	    			  columnData = dataToPlot.slice(4);
	    			  if (columnData.length > 0) {
	    				  element.removeClass("big-donutchart").addClass("medium-donutchart");
	    				  this.drawVerticalBarChart(verticalChart,columnData);
	    			  }
		    		  this.drawMorrisChart(element,donutData);
	    		  }
    		  } else {
    			  element.addClass("no-data-found");
    		  }
    	  };
	}]);

	/**
	 * Directive for HeatMap
	 */
	msDirective.directive('donutchart', ['$rootScope','$http', 'util', 'msChartService',
	                                     function($rootScope,$http,util,msChartService) {
	    return {
	      restrict: 'E',
	      template: "<div class='donut loading'></div>",
	      replace: true,
	        // observe and manipulate the DOM
	      link: function($scope, element, attrs) {
	    	  
	    	  // gets data initially and draws all charts
	    	  var getDataAndDraw = function()  {
	    		  var data = {
		    			  'params' : util.getParamsFromFilter($rootScope.filters)
		    	  };
	    		  var chartMetaData = attrs.chartname + "," + attrs.columnname +  "," + attrs.columntype + "," + attrs.charttype;
	    		  $j.extend(data.params, {'chartmetadata' :chartMetaData } );
	    		  
	    		  var url = '';
	    		  if (attrs.chartname == 'Network Group') {
	    			  url = 'microsegment/networkgroup/'
	    		  } else if (attrs.chartname == 'Other Countries Traveled'){
	    			  url = 'microsegment/otherCountriesTraveled';
	    		  } else {
    				  url = 'microsegment/graph/';
	    		  }
	    		  element.html('');
	    		  var verticalChart = $j('#column-chart-'+attrs.chartname.replace(/ /g,''));
	    		  var horizontalChart = $j('#bar-chart-'+attrs.chartname.replace(/ /g,''));
	    		  verticalChart.html('');
	    		  horizontalChart.html('');
	    		  element.removeClass("loading");
	    		  element.addClass("loading");
		    	  $http.get(url , data).success(function(result) {
		    		  $scope.msdata = result.data;
		    		  
		    		  $scope.title[attrs.chartname] = result.attrName;
		    		 
		    		  msChartService.changeAttributeMeasure(result.data,$rootScope.attributemeasure, attrs, element);
		    		  element.removeClass("loading");
		    	  }).error(function(data, status, headers, config) {
		    		  element.removeClass("loading");
		    		  element.addClass("internal-error");
		    	  });
	    	  };
	    	  
	    	  // get data and draw chart
	    	  getDataAndDraw();
	    	  
	    	  $scope.$watch('microsegmentdaterange', function(oldValue, newValue) {
	    		  if (oldValue != newValue) {
	    			  getDataAndDraw();
	    		  } 
	    	  },true);
	    	  
	    	  $scope.$watch('microsegmentrefresh', function(oldValue, newValue) {
	    		  if (oldValue != newValue && oldValue != null) {
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