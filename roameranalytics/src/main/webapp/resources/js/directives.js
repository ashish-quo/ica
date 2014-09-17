( function () {
	var appDirectives = angular.module("app.directives",["highcharts-ng"]);
	
	 function getColumnChart(categ, data) {
			return {
	            chart: {
	                type: 'column',
					height: 170,
	            },
	            title: {
	                text: ''
	            },
	            subtitle: {
	                text: ''
	            },
	            xAxis: {
	                
	                categories: categ,
	                title: {
	                    text: null
	                }
	            },
	            yAxis: {
	                
	                min: 0,
	                gridLineWidth: 0,
	                minorGridLineWidth: 0,
	                title: {
	                    text: 'Count',
	                    align: 'high'
	                },
	                labels: {
	                    overflow: 'justify'
	                }
	            },
	            tooltip: {
	                valueSuffix: ' %'
	            },
	            plotOptions: {
	                bar: {
	                    dataLabels: {
	                        enabled: true
	                    }
	                }
	           
	            },
	            
	            credits: {
	                enabled: false
	            },
	            series: [{
	                showInLegend:false,
	                data: data,
	   
	            }],
	            colors: ['#5dadb2'],
	        };
	}

	
	/**
	 * Directive for roaming trends
	 */
	appDirectives.directive('trends', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'trends',
	    };
	  });
	
	/**
	 * Directive for microsegment
	 */
	appDirectives.directive('microsegment', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'microsegment',
	    };
	  });
	
	/**
	 * Directive for HeatMap
	 */
	appDirectives.directive('heatMap', function() {
	    return {
	      restrict: 'E',
	      templateUrl: 'heatMap',
	    };
	  });
	
	/**
	 * Directive for HeatMap
	 */
	appDirectives.directive('donutchart', ['$rootScope','$http', 'util', function($rootScope,$http,util) {
	    return {
	      restrict: 'E',
	      template: '<div></div>',
	      replace: true,
	        // observe and manipulate the DOM
	      link: function($scope, element, attrs) {
	    	  var getDataAndDraw = function()  {
	    		  element.html('');
	    		  //$j('#column-chart-'+attrs.chartname).html('');
	    		  var data = {
		    			  'params' : util.getParamsFromFilter($rootScope.filters)
		    	  };
	    		  var chartMetaData = attrs.chartname + "," + attrs.columnname +  "," + attrs.columntype;
	    		  $j.extend(data.params, {'chartmetadata' :chartMetaData } );
		    	  $http.get("microsegment/graph/" , data).success(function(result) {
		    		  $scope.title[attrs.chartname] = result.attrName;
//		    		  var donutData ;
//		    		  var columnData;
//		    		  if (result.data.length > 4) {
//		    			  donutData = result.data.slice(0,3);
//		    			  columnData = result.data.slice(4);
//		    			  $j('#column-chart-'+attrs.chartname).highcharts(getColumnChart(columnData.map(function(obj) {
//		    				  return obj.label;
//		    			  }), columnData.map(function(obj) {
//		    				  return obj.value;
//		    			  })));
//		    			  element.removeClass("big-donutchart").addClass("medium-donutchart")
//		    		  } else {
//		    			  donutData = result.data;
//		    		  }
		    		  
		    		  Morris.Donut({
			    		  element: element,
			    		  data:result.data,
				          colors: ['#fbe591','#cbe8a7','#f3ba83']
			    	  });
		    		  
		    	  }).error(function(data, status, headers, config) {
		    		  //$scope.$parent.error = data.message;
		    	  });
	    	  }
	    	  
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
	      }
	    };
	  }]);

})();