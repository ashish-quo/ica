(function(){
	var utillity = angular.module("app.utility",[]);
	
	/**
	 * Utility service. Contains some utility methods that can be used in any controller
	 */
	utillity.factory("util", [function () {
		utilityService = {};
		
		utilityService.normalizeDate = function(num) {
			if (num < 10) {
				return "0"+num;
			} else 
				return ""+num;
		};
		
		utilityService.getDateString = function (day, month, year) {
			return utilityService.normalizeDate(day) + "/" 
			+ utilityService.normalizeDate(month) + "/" 
			+ ("" + year).slice(2);
		};
		
		// gives date range start and end of the week by week number
		utilityService.getDateRangeOfWeek = function getDateRangeOfWeek(weekNo){
		    var d1 = new Date();
		    numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
		    d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
		    var weekNoToday = d1.getWeek();
		    var weeksInTheFuture = eval( weekNo - weekNoToday );
		    d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
		    var rangeIsFrom = utilityService.getDateString(d1.getDate(),d1.getMonth()+1,d1.getFullYear());
		    d1.setDate(d1.getDate() + 6);
		    var rangeIsTo = utilityService.getDateString(d1.getDate(),d1.getMonth()+1,d1.getFullYear());
		    return {
		    		"from":rangeIsFrom,
		    		"to": rangeIsTo
		    }
		};
		
		utilityService.getDateRangeOfCurrentMonth = function getDateRangeOfCurrentMonth() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			var endTemp = new Date(now.getFullYear(),now.getMonth() + 1,0);
			var rangeIsFrom =  utilityService.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			var rangeIsTo = utilityService.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			return {
	    		"from":rangeIsFrom,
	    		"to": rangeIsTo
			}
		};
		
		/**
		 * Transforms filters so that they are ready to be sent in requrest
		 */
		utilityService.getParamsFromFilter = function (filters) {
			// transform attribute filters
			var attrs = '';
			for (var key in filters.attributes) {
				  if (filters.attributes.hasOwnProperty(key)) {
				    attrs += '' + key + ':' + $j.map(filters.attributes[key], function(obj) {
				    	return obj.value;
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
		    	return obj.countryId;
		    }).join(",");
			
			var params = { 'dateRangeFrom' : filters.dateRangeFrom,
				'dateRangeTo': filters.dateRangeTo,
				'attributes' : attrs,
				'countries' : countries,
				'personas': personas
			}
			return params;
		};
		
		/**
		 * Returns json data for bar charts in microsegment
		 */
		utilityService.getMSBarChart = function (categories, data) {
			return {
				chart : {
					type : 'bar',
					height : 150,
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					categories : categories,
					title : {
						text : null
					},
					labels: {
		                enabled: true,
		                formatter: function() {
		                    return "<span title='"+this.value+"'>" + ((this.value.toString().length > 10) ?(this.value.toString().substring(0,7) + '...'):this.value) + '</span>';
		                },
		                useHTML: true
					}
				},
				yAxis : {
					min : 0,
					gridLineWidth : 0,
					minorGridLineWidth : 0,
					title : {
						text : '',
						align : 'high'
					},
					labels : {
						overflow : 'justify'
					}
				},
				tooltip : {
					shared : false,
					useHTML : true,
					headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
					pointFormat : '<tr><td style="color:{series.color};padding:0">Value: </td>'
							+ '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
					footerFormat : '</table>'
				},
				plotOptions : {
					bar : {
						colorByPoint : true,
						dataLabels : {
							enabled : true
						}
					}

				},
				credits : {
					enabled : false
				},
				series : [ {
					showInLegend : false,
					data : data,

				} ],
				colors : [ '#5dadb2', '#ee9d4e' ]
			};
		};
		
		/**
		 * Returns json data for column charts in microsegment
		 */
		utilityService.getMSColumnChart = function (categories, data) {
			return {
				chart : {
					type : 'column',
					height : 170
				},
				title : {
					text : ''
				},
				subtitle : {
					text : ''
				},
				xAxis : {
					min : 0,
					max : categories.length == 2 ? 1 : (categories.length == 1 ? 0 : 2),
					categories : categories,
					labels: {
		                enabled: true,
		                formatter: function() {
		                    return "<span title='"+this.value+"'>" + ((this.value.toString().length > 8) ?(this.value.toString().substring(0,5) + '...'):this.value) + '</span>';
		                },
		                useHTML: true
					}
				},
				scrollbar: {
					enabled: categories.length > 3 ? true : false,
			        height: 11
			    },
				yAxis : {
					min : 0,
					gridLineWidth : 0,
					minorGridLineWidth : 0,
					title : {
						text : '',
						align : 'high'
					},
					labels : {
						overflow : 'justify'
					}
				},
				plotOptions: {
	                column: {
	                    pointPadding: 0.2,
	                    borderWidth: 0
	                }
	            },
				tooltip : {
					shared : false,
					useHTML : true,
					headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
					pointFormat : '<tr><td style="color:{series.color};padding:0">Value: </td>'
							+ '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
					footerFormat : '</table>'
				},
				credits : {
					enabled : false
				},
				series : [ {
					showInLegend : false,
					data : data
				} ],
				colors : [ '#5dadb2' ]
			};
		};
		
		return utilityService;
	}]);
	
})();