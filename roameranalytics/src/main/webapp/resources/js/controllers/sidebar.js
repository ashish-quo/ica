(function(){
	var sidebar = angular.module("app.sidebar",[]);
	/**
	 * Controller for sidebar actions. Select/un-select of attributes, countries and corresponding actions
	 * are defined here.
	 */
	sidebar.controller('SidebarController',
			['$scope','$rootScope', '$http', 'util', '$location', 'httpService','httpNoDataService', 'pendingRequests',
			 function($scope,$rootScope,$http,util,$location,httpService,httpNoDataService, pendingRequests) {
		
		// filters object, it will contain the information of selected attributes, countries 
		$rootScope.filters = {
				attributes : {},
				personas : new Array(),
				countries : new Array(),
				dateRangeFrom : '',
				dateRangeTo : ''
		};
		// initialize other countries traveled
		$rootScope.otherCountriesTraveled = {};
		$rootScope.countryCategories = new Array();
		$rootScope.countriesFromList = new Array();
		$rootScope.attributeQuery = {};
		$rootScope.countryQuery = {};
		$scope.requestCount=0;
		
		//Custom Date range selector
		$j('#date-range').daterangepicker(null, function(start, end, label) {
			pendingRequests.cancelAll();
			$rootScope.filters.dateRangeFrom = start.format('DD/MM/YY');
			$rootScope.filters.dateRangeTo = end.format('DD/MM/YY');
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			$rootScope.$apply();
			PopulateAttributeOnDateChange();
		});
		
		// Setting default date range to current week
		var currentWeek = new Date().getWeek();
		var defaultDateRange = util.getDateRangeOfCurrentMonth();
		$rootScope.filters.dateRangeFrom = defaultDateRange.from;
		$rootScope.filters.dateRangeTo = defaultDateRange.to;
		
		// added by cheshta for hide and show angular {{}}
		$j("#display-cutdate").show();
		$j("#logolable").show();
		// Get all the attributes to be shown in left panel
		var data = {
				'params' : util.getParamsFromFilter($rootScope.filters)
		};
		httpService.get($scope.roamType + '/getAttributes', data).success(function(data) {
			$scope.attributes = data;
			$rootScope.$broadcast("refresh-heatmap-home");
			$j('.home-backdrop').hide();
		}).error(function(data, status, headers, config) {
	        $rootScope.error = data.message;
	        $j("#map-container").addClass("donut").addClass("loading");
	        $j('.home-backdrop').hide();
	    });
		
		// Getl all the countries to be shown in left panel
		httpService.get($scope.roamType + "/getCountries", data).success(function (data) {
			$scope.countries = data;
		}).error(function(data, status, headers, config) {
			 $rootScope.error = 'Internal server error';
	    });
		
		/*Function to populate attributes on selected date range - added by smruti*/
		function PopulateAttributeOnDateChange()
		{
			$j('.home-backdrop').show();
			
			// Reset filters on refresh of attributes
			$rootScope.filters.attribute= {};
			$rootScope.filters.personas= new Array();
			$rootScope.filters.countries= new Array();
			
			var data = {
					'params' : util.getParamsFromFilter($rootScope.filters)
			};
			
			
			
			// Getl all the countries to be shown in left panel
			httpService.get($scope.roamType + "/getCountries", data).success(function (countryData) {
				$scope.countries = countryData;
				httpService.get($scope.roamType + '/getAttributes', data).success(function(attributeData) {
					$j('.home-backdrop').hide();
					$scope.attributes = attributeData;
					if ($rootScope.tabIndex == 0) {
						$rootScope.$broadcast("refresh-heatmap-home");
					}else if ($rootScope.tabIndex == 1) {
						$rootScope.$broadcast("refresh-roaming-trends");
						$rootScope.$broadcast("refresh-roaming-statistics-trends");
					} else if ($rootScope.tabIndex == 2) {
						$rootScope.$broadcast("refresh-microsegment-daterange",'thismonth');
						$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
					}
						
				}).error(function(data, status, headers, config) {
					$j('.home-backdrop').hide();
			        $rootScope.error = 'Internal server error';
			        if ($rootScope.tabIndex == 0) {
						$rootScope.$broadcast("refresh-heatmap-home");
					}else if ($rootScope.tabIndex == 1) {
						$rootScope.$broadcast("refresh-roaming-trends");
						$rootScope.$broadcast("refresh-roaming-statistics-trends");
					} else if ($rootScope.tabIndex == 2) {
						$rootScope.$broadcast("refresh-microsegment-daterange",'thismonth');
						$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
					}
			       
			    });
				
			}).error(function(data, status, headers, config) {
				 $rootScope.error = 'Internal server error';
				 httpService.get($scope.roamType + '/getAttributes', data).success(function(data) {
					 $j('.home-backdrop').hide();
						$scope.attributes = data;
						 if ($rootScope.tabIndex == 0) {
								$rootScope.$broadcast("refresh-heatmap-home");
							}else if ($rootScope.tabIndex == 1) {
								$rootScope.$broadcast("refresh-roaming-trends");
								$rootScope.$broadcast("refresh-roaming-statistics-trends");
							} else if ($rootScope.tabIndex == 2) {
								$rootScope.$broadcast("refresh-microsegment-daterange",'thismonth');
								$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
							}
					
					}).error(function(data, status, headers, config) {
						$j('.home-backdrop').hide();
				        $rootScope.error = data.message;
				        if ($rootScope.tabIndex == 0) {
							$rootScope.$broadcast("refresh-heatmap-home");
						}else if ($rootScope.tabIndex == 1) {
							$rootScope.$broadcast("refresh-roaming-trends");
							$rootScope.$broadcast("refresh-roaming-statistics-trends");
						} else if ($rootScope.tabIndex == 2) {
							$rootScope.$broadcast("refresh-microsegment-daterange",'thismonth');
							$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
						}
				       
				    });
				 
		    });
			
			
			
			
		}
		
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
			pendingRequests.cancelAll();
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			var endTemp = new Date(now.getFullYear(),now.getMonth() + 1,0);
			$rootScope.filters.dateRangeFrom =  util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			PopulateAttributeOnDateChange();			
		};
		
		/**
		 * Function for calculating last months's date range
		 */
		$scope.lastMonth = function() {
			pendingRequests.cancelAll();
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth()-1,1);
			var endTemp = new Date(now.getFullYear(),now.getMonth(),0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			PopulateAttributeOnDateChange();		
		};
		
		/**
		 * Function for calculating this quarter's date range
		 */
		$scope.thisQuarter = function() {
			pendingRequests.cancelAll();
			var now = new Date();
			var quarter = Math.floor((now.getMonth() + 3) / 3);
			var quarterEndMonth = quarter * 3;
			var quarterStartMonth = quarterEndMonth - 3;
			var startTemp = new Date(now.getFullYear(),quarterStartMonth,1);
			
			var endTemp = new Date(now.getFullYear(),quarterEndMonth,0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			PopulateAttributeOnDateChange();		
		};
		
		/**
		 * Function for calculating last quarter's date range
		 */
		$scope.lastQuarter = function() {
			pendingRequests.cancelAll();
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
			PopulateAttributeOnDateChange();
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
			
			var checked =  $j(element).closest('form').find('input.sub-check:checked');
			var allsubcheck =  $j(element).closest('form').find('input.sub-check');
			
			var refresh = true;
			if (checked.length == 0 || allsubcheck.length == checked.length) {
				refresh = false;
			}
			
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
			
			if (refresh) {
				pendingRequests.cancelAll();
				if ($rootScope.tabIndex == 0) {
					$rootScope.$broadcast("refresh-heatmap-home");
				}else if ($rootScope.tabIndex == 1) {
					$rootScope.$broadcast("refresh-roaming-trends");
					$rootScope.$broadcast("refresh-roaming-statistics-trends");
				}  else if ($rootScope.tabIndex == 2) {
					$rootScope.$broadcast("refresh-microsegment-attribute");
					$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
				}
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
		
		$scope.updateNetworkFilter = function (attrId, catId) {
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
		}
		
		$scope.applyNetworkFilter  = function (id) {
			pendingRequests.cancelAll();
			var parentElement = $j('input#'+id);
			var columnName = parentElement.attr("db-column");
			var columnType = parentElement.attr("column-type");
			var elementName = parentElement.attr("attr-name");
			var key = elementName + "," + columnName + "," + columnType;
			delete $rootScope.filters.attributes[key];
			if (!parentElement.is(":checked")) {
				
				$j("input.network_sub:checked").each(function () {
					var id = $j(this).attr("id").split("_");
					var name = $j(this).attr("name");
					var value = $j(this).attr("categ-value");
					var attributeId = id[0];
					var catId = id[1];
					 parentElement = $j('input#attr_'+attributeId);
					 columnName = parentElement.attr("db-column");
					 columnType = parentElement.attr("column-type");
					 elementName = parentElement.attr("attr-name");
					 key = elementName + "," + columnName + "," + columnType;
					var attrArray = $rootScope.filters.attributes[key];
					if (attrArray == null) {
						$rootScope.filters.attributes[key] = new Array();
					}
					$rootScope.filters.attributes[key].push({'catId':catId, 'name':name, 'value':value, 'attrId' : attributeId });
				});
			}
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		}
		
		$scope.applyNetworkGroupFilter  = function (id) {
			pendingRequests.cancelAll();// cancell previous requests and start fresh request
			var parentElement = $j('input#'+id);
			var columnName = parentElement.attr("db-column");
			var columnType = parentElement.attr("column-type");
			var elementName = parentElement.attr("attr-name");
			var key = elementName + "," + columnName + "," + columnType;
			delete $rootScope.filters.attributes[key];
			
			if (!parentElement.is(":checked")) {
				$j("input.network_group_sub:checked").each(function () {
					var id = $j(this).attr("id").split("_");
					var name = $j(this).attr("name");
					var value = $j(this).attr("categ-value");
					var attributeId = id[0];
					var catId = id[1];
					 parentElement = $j('input#attr_'+attributeId);
					 columnName = parentElement.attr("db-column");
					 columnType = parentElement.attr("column-type");
					 elementName = parentElement.attr("attr-name");
					 key = elementName + "," + columnName + "," + columnType;
					var attrArray = $rootScope.filters.attributes[key];
					if (attrArray == null) {
						$rootScope.filters.attributes[key] = new Array();
					}
					$rootScope.filters.attributes[key].push({'catId':catId, 'name':name, 'value':value, 'attrId' : attributeId });
				});
			}
			
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
			}else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		}
		
		/**
		 * Refreshes data when an attribute is checked or unchecked
		 */
		$scope.updateAttributeFilter = function(attrId,catId) {
			pendingRequests.cancelAll();
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
		$scope.updateCountryCategory = function(id) {
			var element = $j("#"+id);
			if(element.is(':checked')) {
				if ($j("input[bordering = '1']").length == $j("input[bordering = '1']:checked").length) {
					$j("input#Neighbours").attr("checked","checked");
				}
				if ($j("input[leisure = '1']").length == $j("input[leisure = '1']:checked").length) {
					$j("input#Leisure").attr("checked","checked");
				}
				if ($j("input[leisurepremium = '1']").length == $j("input[leisurepremium = '1']:checked").length) {
					$j("input#Leisurepre").attr("checked","checked");
				}
				if ($j("input[lowgdp = '1']").length == $j("input[lowgdp = '1']:checked").length) {
					$j("input#Lowgdp").attr("checked","checked");
				}
				
			} else {
				if ($j("input[bordering = '1']").length != $j("input[bordering = '1']:checked").length) {
					$j("input#Neighbours").removeAttr("checked");
				}
				if ($j("input[leisure = '1']").length != $j("input[leisure = '1']:checked").length) {
					$j("input#Leisure").removeAttr("checked");
				}
				if ($j("input[leisurepremium = '1']").length != $j("input[leisurepremium = '1']:checked").length) {
					$j("input#Leisurepre").removeAttr("checked");
				}
				if ($j("input[lowgdp = '1']").length != $j("input[lowgdp = '1']:checked").length) {
					$j("input#Lowgdp").removeAttr("checked");
				}
			}
		};
		
		$scope.checkLeisure = function(id) {
			var element = $j("#"+id);
			if(element.is(':checked')) {
				$j("input[leisure = '1']").attr("checked","checked");
			} else {
				$j("input[leisure = '1']").removeAttr("checked");
			}
		}
		
		$scope.checkNeighbours = function(id) {
			var element = $j("#"+id);
			if(element.is(':checked')) {
				$j("input[bordering = '1']").attr("checked","checked");
			} else {
				$j("input[bordering = '1']").removeAttr("checked");
			}
		}
		
		$scope.checkLeisurePermium = function(id) {
			var element = $j("#"+id);
			if(element.is(':checked')) {
				$j("input[leisurepremium = '1']").attr("checked","checked");
			} else {
				$j("input[leisurepremium = '1']").removeAttr("checked");
			}
		}
		
		$scope.checkLowGDP = function(id) {
			var element = $j("#"+id);
			if(element.is(':checked')) {
				$j("input[lowgdp = '1']").attr("checked","checked");
			} else {
				$j("input[lowgdp = '1']").removeAttr("checked");
			}
		}
		
		/**
		 * Action for apply button on country filter
		 */
		$scope.applyCountryFilter = function () {
			pendingRequests.cancelAll();
			$rootScope.filters.countries = new Array();
			var allCountries = $j("#All-countries");
			$rootScope.filters.countries = new Array();
			$rootScope.countriesFromList = new Array();
			$rootScope.countryCategories = new Array();
			if (!allCountries.is(":checked")) {
				var identifiers = new Array();
				var selectedCategories = $j("input.country-category:checked");
				selectedCategories.each(function() {
					var id = $j(this).attr("id");
					var identifier = $j(this).attr("identifier");
					var name = $j(this).attr("lvalue");
					identifiers[identifier] = true;
					$rootScope.countryCategories.push({'id':id,'name':name,'identifier':identifier});
				});
				var checkedCountries = $j("input.country-chk:checked");
				checkedCountries.each(function () {
					var id = $j(this).attr("id");
					var mcc = $j(this).attr("mcc");
					var name = $j(this).attr("name");
					var bordering = $j(this).attr('bordering');
					var leisure = $j(this).attr('leisure');
					var leisurepremium = $j(this).attr('leisurepremium');
					var lowgdp = $j(this).attr('lowgdp');
					var countryIncluded = false;
					if (bordering == '1' && identifiers.bordering == true) {
						countryIncluded = true;
					}
					if ( !countryIncluded && leisure == '1' && identifiers.leisure == true) {
						countryIncluded = true;
					}
					if (!countryIncluded && leisurepremium == '1' && identifiers.leisurepremium == true) {
						countryIncluded = true;
					}
					if (!countryIncluded && lowgdp == '1' && identifiers.lowgdp == true) {
						countryIncluded = true;
					}
					$rootScope.filters.countries.push({'id':id,'name':name,
						'bordering':bordering,
						'leisure':leisure,
						'leisurepremium':leisurepremium,
						'lowgdp':lowgdp,
						"mcc" : mcc});
					
					if (!countryIncluded)
						$rootScope.countriesFromList.push({'id':id,'name':name,
							'bordering':bordering,
							'leisure':leisure,
							'leisurepremium':leisurepremium,
							'lowgdp':lowgdp,
							"mcc" : mcc});
				});
			} 

			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
			} else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			}  else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-country");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		}
		/**
		 * This function removes country filter from filter area on each screen
		 */
		var removeCounryFilter = function (id) {
			$j('#'+id).removeAttr('checked');
			$rootScope.filters.countries = $j.map($rootScope.filters.countries, function(obj) {
				return obj.id == id ? null : obj; 
			});
			$rootScope.countriesFromList = $j.map($rootScope.countriesFromList, function(obj) {
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
			pendingRequests.cancelAll();
			removeCounryFilter(id);
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
			} else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}	
		};
		
		$rootScope.removeCountryCategoryFilter = function(id,identifier) {
			pendingRequests.cancelAll();
			$j('#'+id).removeAttr('checked');
			$j('input['+identifier +"= '1']").removeAttr('checked');
			
			$rootScope.countryCategories = $j.map($rootScope.countryCategories, function(obj) {
				return obj['identifier'] == identifier ? null : obj; 
			});
			
			$rootScope.filters.countries = $j.map($rootScope.filters.countries, function(obj) {
				return obj[identifier] == '1' ? null : obj; 
			});
			
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
			} else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}
		}
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
			pendingRequests.cancelAll();
			removeAttributeFilter(key,attrId,catId);
			if ($rootScope.tabIndex == 0) {
				$rootScope.$broadcast("refresh-heatmap-home");
			} else if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
				$rootScope.$broadcast("refresh-roaming-statistics-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment-attribute");
				$rootScope.$broadcast("refresh-roaming-statistics-microsegment");
			}	
		};
		
		
		$rootScope.$on("add-filter-from-microsegment", function (data, event) {
			$scope.updateAttributeFilter(data.attributeId, data.categoryId);
		});
		
		
	}]);
})();
