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
	            plotOptions: {
	                bar: {
	                    dataLabels: {
	                        enabled: true
	                    }
	                }
	           
	            },
	            tooltip: {
					shared: false,
	                useHTML: true,
	                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">Value: </td>' +
	                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
	                footerFormat: '</table>'
	            },
	            credits: {
	                enabled: false
	            },
	            series: [{
	                showInLegend:false,
	                data: data,
	   
	            }],
	            colors: ['#5dadb2']
	        };
	};
	
	function getBarChart(categ, data) {
		return {
			chart: {
				type: 'bar',
				height: 150,
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
					text: '',
					align: 'high'
				},
				labels: {
					overflow: 'justify'
				}
			},
			tooltip: {
					shared: false,
	                useHTML: true,
	                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">Value: </td>' +
	                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
	                footerFormat: '</table>'
			},
			plotOptions: {
				bar: {
					colorByPoint: true,
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
			colors: ['#5dadb2','#ee9d4e']
		};
	};

	
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
	      template: "<div class='donut loading'></div>",
	      replace: true,
	        // observe and manipulate the DOM
	      link: function($scope, element, attrs) {
	    	  var drawMorrisChart = function(element, data) {
	    		  Morris.Donut({
		    		  element: element,
		    		  data:data,
			          colors: ['#fbe591','#cbe8a7','#f3ba83']
		    	  });
	    	  };
	    	  
	    	  var drawVerticalBarChart = function (element, data) {
	    		  element.highcharts(getColumnChart(data.map(function(obj) {
    				  return obj.label;
    			  }), data.map(function(obj) {
    				  return obj.value;
    			  })));
	    	  };
	    	  
	    	  var drawHorizontalBarChart = function (element, data) {
	    		  element.highcharts(getBarChart(data.map(function(obj) {
    				  return obj.label;
    			  }), data.map(function(obj) {
    				  return obj.value;
    			  })));
	    	  }
	    	  
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
		    		  element.removeClass("loading");
		    		  $scope.title[attrs.chartname] = result.attrName;
		    		  var verticalChart = $j('#column-chart-'+attrs.chartname.replace(/ /g,''));
		    		  var horizontalChart = $j('#bar-chart-'+attrs.chartname.replace(/ /g,''));
		    		  verticalChart.html('');
		    		  horizontalChart.html('');
		    		  if (result.data.length > 0) {
		    			  element.removeClass("no-data-found")
			    		  if (attrs.charttype == '1') {
			    			  drawMorrisChart(element,result.data);
			    		  } else if (attrs.charttype == '2') {
			    			  var donutData ;
				    		  var columnData;
				    		  columnData = result.data.slice(0,2);
				    		  donutData = result.data.slice(3);
				    		  element.removeClass("big-donutchart").addClass("medium-donutchart")
				    		  drawHorizontalBarChart(horizontalChart,columnData);
				    		  drawMorrisChart(element,donutData);
			    		  } else if (attrs.charttype == '3') {
				    		  var donutData ;
				    		  var columnData;
			    			  donutData = result.data.slice(0,3);
			    			  columnData = result.data.slice(4);
			    			  if (columnData.length > 0) {
			    				  element.removeClass("big-donutchart").addClass("medium-donutchart");
			    				  drawVerticalBarChart(verticalChart,columnData);
			    			  }
				    		  drawMorrisChart(element,donutData);
			    		  }
		    		  } else {
		    			  element.addClass("no-data-found")
		    		  }
		    	  }).error(function(data, status, headers, config) {
		    		  element.removeClass("loading");
		    		  element.addClass("internal-error")
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