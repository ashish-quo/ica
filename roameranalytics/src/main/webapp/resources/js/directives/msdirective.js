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
	    		  Morris.Donut({
		    		  element: element,
		    		  data:data,
			          colors: ['#fbe591','#cbe8a7','#f3ba83']
		    	  });
	    	  };
	    	  
	    	  // draws column chart
	    	  var drawVerticalBarChart = function (element, data) {
	    		  element.highcharts(util.getMSColumnChart(data.map(function(obj) {
    				  return obj.label;
    			  }), data.map(function(obj) {
    				  return obj.value;
    			  })));
	    	  };
	    	  
	    	  // draws bar chart
	    	  var drawHorizontalBarChart = function (element, data) {
	    		  element.highcharts(util.getMSBarChart(data.map(function(obj) {
    				  return obj.label;
    			  }), data.map(function(obj) {
    				  return obj.value;
    			  })));
	    	  };
	    	  
	    	  // refreshes chart when microsegment measure is changed
	    	  var changeAttributeMeasure = function(data, measure) {
	    		  
	    		  var verticalChart = $j('#column-chart-'+attrs.chartname.replace(/ /g,''));
	    		  var horizontalChart = $j('#bar-chart-'+attrs.chartname.replace(/ /g,''));
	    		  verticalChart.html('');
	    		  horizontalChart.html('');
	    		  if (data != null && data.length > 0) {
	    			  
	    			  // change value to measure 
	    			  data = data.map(function (obj) {
	 	    			 obj.value = obj[measure];
	 	    			 return obj;
	 	    		  });
	    			  
	    			  // sort data by value
	    			  data = data.sort(function (a, b){
	    				  return b.value - a.value;
	    			  });
	    			  
	    			  element.removeClass("no-data-found")
		    		  if (attrs.charttype == '1') {
		    			  drawMorrisChart(element,data);
		    		  } else if (attrs.charttype == '2') {
		    			  var donutData ;
			    		  var columnData;
			    		  columnData = data.slice(0,2);
			    		  donutData = data.slice(3);
			    		  element.removeClass("big-donutchart").addClass("medium-donutchart")
			    		  drawHorizontalBarChart(horizontalChart,columnData);
			    		  drawMorrisChart(element,donutData);
		    		  } else if (attrs.charttype == '3') {
			    		  var donutData ;
			    		  var columnData;
		    			  donutData = data.slice(0,3);
		    			  columnData = data.slice(4);
		    			  if (columnData.length > 0) {
		    				  element.removeClass("big-donutchart").addClass("medium-donutchart");
		    				  drawVerticalBarChart(verticalChart,columnData);
		    			  }
			    		  drawMorrisChart(element,donutData);
		    		  }
	    		  } else {
	    			  element.addClass("no-data-found")
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
		    		  element.removeClass("loading");
		    		  $scope.title[attrs.chartname] = result.attrName;
		    		  changeAttributeMeasure(result.data,'roamers');
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
	  			changeAttributeMeasure($scope.msdata,newValue);
	  		});
	      }
	    };
	  }]);

})();