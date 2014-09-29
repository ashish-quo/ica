(function(){
	var sidebar = angular.module("app.sidebar",[]);
	/**
	 * Controller for sidebar actions. Select/un-select of attributes, countries and corresponding actions
	 * are defined here.
	 */
	sidebar.controller('SidebarController',
			['$scope','$rootScope', '$http', 'util', function($scope,$rootScope,$http,util) {
		
		// filters object, it will contain the information of selected attributes, countries 
		$rootScope.filters = {
				attributes : {},
				personas : new Array(),
				countries : new Array(),
				otherCountriesTravelled : new Array(),
				dateRangeFrom : '',
				dateRangeTo : ''
		};
		
		
		$rootScope.attributeQuery = {};
		$rootScope.countryQuery = {};
		
		//Custom Date range selector
		$j('#date-range').daterangepicker(null, function(start, end, label) {
			$rootScope.filters.dateRangeFrom = start.format('DD/MM/YY');
			$rootScope.filters.dateRangeTo = end.format('DD/MM/YY');
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			$rootScope.$apply();
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'custom');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		});
		
		// Setting default date range to current week
		var currentWeek = new Date().getWeek();
		var defaultDateRange = util.getDateRangeOfWeek(currentWeek);
		$rootScope.filters.dateRangeFrom = defaultDateRange.from;
		$rootScope.filters.dateRangeTo = defaultDateRange.to;
		
		// Get all the attributes to be shown in left panel
		$http.get('getAttributes').success(function(data) {
			$scope.attributes = data;
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		// Getl all the countries to be shown in left panel
		$http.get("getCountries").success(function (data) {
			$scope.countries = data;
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		/**
		 * Function for calculating current week's date range
		 */
		$scope.thisWeekRange = function() {
			var dateRange = util.getDateRangeOfWeek(new Date().getWeek());
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'thisweek');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
				
			}
		};
		
		/**
		 * Function for calculating last week's date range
		 */
		$scope.lastWeekRange = function() {
			var dateRange = util.getDateRangeOfWeek(new Date().getWeek() - 1);
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'lastweek');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * Function for calculating this month's date range
		 */
		$scope.thisMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			
			$rootScope.filters.dateRangeFrom =  util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(now.getDate(),now.getMonth()+1,now.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'thismonth');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * Function for calculating last months's date range
		 */
		$scope.lastMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth()-1,1);
			var endTemp = new Date(now.getFullYear(),now.getMonth(),0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'lastmonth');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * Function for calculating this quarter's date range
		 */
		$scope.thisQuarter = function() {
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var quarterEndMonth = quarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'thisquarter');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * Function for calculating last quarter's date range
		 */
		$scope.lastQuarter = function() {
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var lastQuarter = quarter - 1;
			if (lastQuarter <= 0) {
				now.setFullYear(now.getFullYear() - 1);
				lastQuarter = 4;
			}
			var quarterEndMonth = lastQuarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-daterange",'lastquarter');
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * function for filtering attributes from search box
		 */
		$scope.filterHiddenAttr = function(id,text) {
			if ($scope.query == null || $scope.query.displayText == '' )
				return true;
			else {
				var result = text.toUpperCase().indexOf($scope.query.displayText.toUpperCase()) != -1;
				if (!result) {
					var nextSib = $j('#li_'+id).next("li.sub_"+id);
					if (nextSib.length > 0)
						result = true;
				}
				return result;
			}
		};
		
		/**
		 * Actions for select all checkbox of personas
		 */
		$scope.selectAllPersonas = function(id) {
			var element = $j("input#"+id);
			var checkboxes = $j(element).closest('form').find(':checkbox');
			if($j(element).is(':checked')) {
				checkboxes.attr('checked', 'checked');
			} else {
				checkboxes.removeAttr('checked');
			}
			$rootScope.filters.personas = new Array();
			var selectAll = $j("input.all-persona:not(:checked)");
			$j(selectAll).closest("form").find("input.persona-check:checked").each(function () {
				var id = $j(this).attr("id");
				var name =  $j(this).attr("name");
				$rootScope.filters.personas.push({'id':id,'name':name});
			});
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}
		};
		
		/**
		 * Actions for select/de-select all checkbox of attributes
		 */
		$scope.clearSelectAllAttribute = function (attrId) {
			var element = $j('input#attr_'+attrId);
			var columnName = element.attr("db-column");
			var columnType = element.attr("column-type");
			var elementName = element.attr("name");
			var checkboxes = $j(element).closest('form').find(':checkbox');
			if($j(element).is(':checked')) {
				checkboxes.attr('checked', 'checked');
			} else {
				checkboxes.removeAttr('checked');
			}
			$rootScope.filters.attributes = {};
			var selectAll = $j("input.all-attr:not(:checked)");
			$j(selectAll).closest("form").find("input.sub-check:checked").each(function () {
				var id = $j(this).attr("id").split("_");
				var name = $j(this).attr("name");
				var value = $j(this).attr("categ-value");
				var attrId = id[0];
				var catId = id[1];
				var key = elementName + "," + columnName + "," + columnType;
				var attrArray = $rootScope.filters.attributes[key];
				if (attrArray == null) {
					$rootScope.filters.attributes[key] = new Array();
				}
				$rootScope.filters.attributes[key].push({'catId':catId, 'name':name,'value':value, 'attrId':attrId });
			});
			
			if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
			}
		};
		
		/**
		 * This function executes when a persona is checked/unchecked. Data is refreshed after each check/uncheck
		 */
		$scope.updatePersonaFilter = function(id) {
			$rootScope.filters.personas = new Array();
			
			var element = $j("input#"+id);
			if($j(element).is(':checked')) {
				$j(element).attr('checked', 'checked');
			} else {
				$j(element).removeAttr('checked');
			}
			var parent = $j(element).closest('form');
			
			if($j(parent).find(".persona-check").length == $j(".persona-check:checked").length) {
				$j(parent).find('input.Select-all').attr("checked", "checked");
			} else {
				$j(parent).find('input.Select-all').removeAttr("checked");
			}
			
			var selectAll = $j("input.all-persona:not(:checked)");
			$j(selectAll).closest("form").find("input.persona-check:checked").each(function () {
				var id = $j(this).attr("id");
				var name =  $j(this).attr("name");
				$rootScope.filters.personas.push({'id':id,'name':name});
			});
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} 
		};
		
		/**
		 * Refreshes data when an attribute is checked or unchecked
		 */
		$scope.updateAttributeFilter = function(attrId,catId) {
			$rootScope.filters.attributes = {};
			var element = $j('input#'+attrId+'_'+catId);
			
			if($j(element).is(':checked')) {
				$j(element).attr('checked', 'checked');
			} else {
				$j(element).removeAttr('checked');
			}
			var parent = $j(element).closest('form');
			if($j(parent).find('.sub-check').length == $j(parent).find(".sub-check:checked").length) {
				$j(parent).find('.Select-all').attr("checked", "checked");
			} else {
				$j(parent).find('.Select-all').removeAttr("checked");
			}
			
			
			var selectAll = $j("input.all-attr:not(:checked)");
			$j(selectAll).closest("form").find("input.sub-check:checked").each(function () {
				var id = $j(this).attr("id").split("_");
				var name = $j(this).attr("name");
				var value = $j(this).attr("categ-value");
				var attributeId = id[0];
				var catId = id[1];
				var parentElement = $j('input#attr_'+attributeId);
				var columnName = parentElement.attr("db-column");
				var columnType = parentElement.attr("column-type");
				var elementName = parentElement.attr("attr-name");
				var key = elementName + "," + columnName + "," + columnType;
				var attrArray = $rootScope.filters.attributes[key];
				if (attrArray == null) {
					$rootScope.filters.attributes[key] = new Array();
				}
				$rootScope.filters.attributes[key].push({'catId':catId, 'name':name, 'value':value, 'attrId' : attributeId });
			});
			
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * Refreshes the data when country is changed
		 */
		$scope.updateCountryFilter = function() {
			$rootScope.filters.countries = new Array();
			$j("input.country-chk:checked").each(function () {
				var id = $j(this).attr("id");
				var name = $j(this).attr("name");
				var brodering = $j(this).attr('bordering')
				$rootScope.filters.countries.push({'id':id,'name':name,'bordering':brodering});
			});
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-country");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * This function removes country filter from filter area on each screen
		 */
		var removeCounryFilter = function (id) {
			$j('#'+id).removeAttr('checked');
			$rootScope.filters.countries = $j.map($rootScope.filters.countries, function(obj) {
				return obj.id == id ? null : obj; 
			});
		};
		
		/**
		 * This function removes attribute filter from filter area on each screen
		 */
		var removeAttributeFilter = function (key,attrId,catId ) {
			$j('#'+attrId + "_"+catId).removeAttr('checked');
			$rootScope.filters.attributes[key] = $j.map($rootScope.filters.attributes[key], function(obj) {
				return obj.catId == catId ? null : obj; 
			});
			if ($rootScope.filters.attributes[key].length == 0) {
				delete $rootScope.filters.attributes[key];
			}
		};
		
		/**
		 * This function removes persona filter from filter area on each screen
		 */
		
		var removePersonaFilter = function (id) {
			$j('#'+id).removeAttr('checked');
			$rootScope.filters.personas = $j.map($rootScope.filters.personas, function(obj) {
				return obj.id == id ? null : obj; 
			});
		};
		
		/**
		 * Refreshes charts and data when a country filter is removed from filter area
		 */
		$rootScope.removeCounryFilter = function(id,refresh) {
			removeCounryFilter(id);
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-country");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		};
		
		/**
		 * Refreshes charts and data when a persona filter is removed from filter area
		 */
		$rootScope.removePersonaFilter = function(id,refresh) {
			removePersonaFilter(id);
			$rootScope.$broadcast("refresh-roaming-trends");
		};
		
		/**
		 * Refreshes charts and data when an attribute filter is removed from filter area
		 */
		$rootScope.removeAttributeFilter = function(key,attrId,catId,refresh) {
			removeAttributeFilter(key,attrId,catId);
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
				$rootScope.$broadcast("refresh-bubblechart-home");
				$rootScope.$broadcast("refresh-roaming-statistics-home");
			} else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}	
		};
		
		/**
		 * Call when exclude neighbors is checked
		 */
		$scope.$watch('excludeNbrs', function () {
			$scope.updateCountryFilter();
		});
		
		$rootScope.$on("add-filter-from-microsegment", function (data, event) {
			$scope.updateAttributeFilter(data.attributeId, data.categoryId);
		});
		
	}]);
})();
