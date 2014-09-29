(function() {
	var msDirective = angular.module("app.msdirective", []);

	/**
	 * Directive for HeatMap
	 */
	msDirective.directive('donutchart', ['$rootScope','$http', 'util', function($rootScope,$http,util) {
	    return {
	      restrict: 'E',
	      template: "<div class='donut loading'></div>",
	      replace: true,
	        // observe and manipulate the DOM
	      link: function($scope, element, attrs) {
	    	  // draws donut chart
	    	  var drawMorrisChart = function(element, data) {
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
		    	        	  $j(target).attr('checked', 'checked');
		    	        	  var filter = {'attributeId':filterId[0],'categoryId':filterId[1]};
		    	        	  $rootScope.$broadcast('add-filter-from-microsegment',filter);
		    	          },
		    	        },
		    	        donut: {
		    	          title: "",
		    	          width:50
		    	        }
		    	      });
	    		  }

	    	  };
	    	  
	    	  // draws column chart
	    	  var drawVerticalBarChart = function (element, data) {
	    		  chart2 =  element.highcharts(util.getMSColumnChart(data.map(function(obj) {
    				  return obj[0];
    			  }), data.map(function(obj) {
    				  return obj[1];
    			  })));
	    	  };
	    	  
	    	  // draws bar chart
	    	  var drawHorizontalBarChart = function (element, data) {
	    		  chart1 = element.highcharts(util.getMSBarChart(data.map(function(obj) {
    				  return obj[0];
    			  }), data.map(function(obj) {
    				  return obj[1];
    			  })));
	    	  };
	    	  
	    	  // refreshes chart when microsegment measure is changed
	    	  var changeAttributeMeasure = function(data, measure) {
	    		  var dataToPlot = data[measure];
	    		  var verticalChart = $j('#column-chart-'+attrs.chartname.replace(/ /g,''));
	    		  var horizontalChart = $j('#bar-chart-'+attrs.chartname.replace(/ /g,''));
	    		  verticalChart.html('');
	    		  horizontalChart.html('');
	    		  
	    		  if (dataToPlot != null && dataToPlot.length > 0) {
	    			 
	    			  element.removeClass("no-data-found")
		    		  if (attrs.charttype == '1') {
		    			  drawMorrisChart(element,dataToPlot);
		    		  } else if (attrs.charttype == '2') {
		    			  var donutData ;
			    		  var columnData;
			    		  columnData = dataToPlot.slice(0,2);
			    		  donutData = dataToPlot.slice(3);
			    		  element.removeClass("big-donutchart").addClass("medium-donutchart")
			    		  drawHorizontalBarChart(horizontalChart,columnData);
			    		  drawMorrisChart(element,donutData);
		    		  } else if (attrs.charttype == '3') {
			    		  var donutData ;
			    		  var columnData;
		    			  donutData = dataToPlot.slice(0,3);
		    			  columnData = dataToPlot.slice(4);
		    			  if (columnData.length > 0) {
		    				  element.removeClass("big-donutchart").addClass("medium-donutchart");
		    				  drawVerticalBarChart(verticalChart,columnData);
		    			  }
			    		  drawMorrisChart(element,donutData);
		    		  }
	    		  } else {
	    			  element.addClass("no-data-found");
	    		  }
	    	  };
	    	  
	    	  // gets data initially and draws all charts
	    	  var getDataAndDraw = function()  {
	    		  element.html('');
	    		  
	    		  var data = {
		    			  'params' : util.getParamsFromFilter($rootScope.filters)
		    	  };
	    		  
	    		  var chartMetaData = attrs.chartname + "," + attrs.columnname +  "," + attrs.columntype + "," + attrs.charttype;
	    		  $j.extend(data.params, {'chartmetadata' :chartMetaData } );
	    		  
	    		  var url = '';
	    		  if (attrs.chartname == 'Network Group') {
	    			  url = 'microsegment/networkgroup/'
	    		  } else {
	    			  url = 'microsegment/graph/';
	    		  }
	    		  
		    	  $http.get(url , data).success(function(result) {
		    		  $scope.msdata = result.data;
		    		  
		    		  $scope.title[attrs.chartname] = result.attrName;
		    		 
		    		  changeAttributeMeasure(result.data,$rootScope.attributemeasure);
		    		  element.removeClass("loading");
		    	  }).error(function(data, status, headers, config) {
		    		  element.removeClass("loading");
		    		  element.addClass("internal-error")
		    	  });
	    	  };
	    	  
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
	    			  changeAttributeMeasure($scope.msdata,newValue);
	    		  }
	  		});
	      }
	    };
	  }]);

})();