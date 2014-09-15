(function(){
	var sidebar = angular.module("app.sidebar",[]);
	/**
	 * Controller for sidebar actions
	 */
	sidebar.controller('SidebarController',
			['$scope','$rootScope', '$http', 'util', function($scope,$rootScope,$http,util) {
				
		$rootScope.filters = {
				attributes : {},
				tempAttributes : {},
				personas : new Array(),
				countries : new Array(),
				dateRangeFrom : '',
				dateRangeTo : ''
		};
		
		//Date range selector
		$j('#date-range').daterangepicker(null, function(start, end, label) {
			$rootScope.filters.dateRangeFrom = start.format('DD/MM/YY');
			$rootScope.filters.dateRangeTo = end.format('DD/MM/YY');
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			$rootScope.$apply();
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		});
		var currentWeek = new Date().getWeek();
		var defaultDateRange = util.getDateRangeOfWeek(currentWeek);
		$rootScope.filters.dateRangeFrom = defaultDateRange.from;
		$rootScope.filters.dateRangeTo = defaultDateRange.to;
		
		$http.get('getAttributes').success(function(data) {
			$scope.attributes = data;
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		$http.get("getCountries").success(function (data) {
			$scope.countries = data;
		}).error(function(data, status, headers, config) {
	        //$scope.$parent.error = data.message;
	    });
		
		$scope.thisWeekRange = function() {
			var dateRange = util.getDateRangeOfWeek(new Date().getWeek());
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment",'thisweek');
			}
		};
		
		$scope.lastWeekRange = function() {
			var dateRange = util.getDateRangeOfWeek(new Date().getWeek() - 1);
			$rootScope.filters.dateRangeFrom = dateRange.from;
			$rootScope.filters.dateRangeTo = dateRange.to;
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment",'lastweek');
			}
		};
		
		$scope.thisMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth(),1);
			
			$rootScope.filters.dateRangeFrom =  util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(now.getDate(),now.getMonth()+1,now.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment",'thismonth');
			}
		};
		
		$scope.lastMonth = function() {
			var now = new Date();
			var startTemp = new Date(now.getFullYear(),now.getMonth()-1,1);
			var endTemp = new Date(now.getFullYear(),now.getMonth(),0);
			
			$rootScope.filters.dateRangeFrom = util.getDateString(startTemp.getDate(),startTemp.getMonth()+1,startTemp.getFullYear());
			$rootScope.filters.dateRangeTo = util.getDateString(endTemp.getDate(),endTemp.getMonth()+1,endTemp.getFullYear());
			
			$rootScope.filters.dateRange = $rootScope.dateRangeFrom + $rootScope.dateRangeTo;
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment",'lastmonth');
			}
		};
		
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
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment",'thisquarter');
			}
		};
		
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
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			} else if ($rootScope.tabIndex == 2) {
				$rootScope.$broadcast("refresh-microsegment",'lastquarter');
			}
		};
		
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
		
		$scope.selectAllPersonas = function(id) {
			var element = $j("input#"+id);
			var checkboxes = $j(element).closest('form').find(':checkbox');
			if($j(element).is(':checked')) {
				checkboxes.attr('checked', 'checked');
			} else {
				checkboxes.removeAttr('checked');
			}
			$rootScope.filters.personas = new Array();
			$rootScope.filters.personasText = new Array();
			var selectAll = $j("input.all-persona:not(:checked)");
			$j(selectAll).closest("form").find("input.persona-check:checked").each(function () {
				var id = $j(this).attr("id");
				var name =  $j(this).attr("name");
				$rootScope.filters.personas.push({'id':id,'name':name});
				$rootScope.filters.personasText.push(name);
			});
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		$scope.clearSelectAllAttribute = function (attrId,catId) {
			var element = $j('input#attr_'+attrId);
			var checkboxes = $j(element).closest('form').find(':checkbox');
			if($j(element).is(':checked')) {
				checkboxes.attr('checked', 'checked');
			} else {
				checkboxes.removeAttr('checked');
			}
			$rootScope.filters.attributes = {};
			$rootScope.filters.attributesText = new Array();
			var selectAll = $j("input.all-attr:not(:checked)");
			$j(selectAll).closest("form").find("input.sub-check:checked").each(function () {
				var id = $j(this).attr("id").split("_");
				var name = $j(this).attr("name");
				var attrId = id[0];
				var catId = id[1];
				$rootScope.filters.attributesText.push(name);
				var attrArray = $rootScope.filters.attributes[attrId];
				if (attrArray == null) {
					$rootScope.filters.attributes[attrId] = new Array();
				}
				$rootScope.filters.attributes[attrId].push({'catId':catId, 'name':name });
			});
		};
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
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
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
				var attrId = id[0];
				var catId = id[1];
				var attrArray = $rootScope.filters.attributes[attrId];
				if (attrArray == null) {
					$rootScope.filters.attributes[attrId] = new Array();
				}
				$rootScope.filters.attributes[attrId].push({'catId':catId, 'name':name });
			});
			
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		$scope.updateCountryFilter = function() {
			$rootScope.filters.countries = new Array();
			$j("input.country-chk:checked").each(function () {
				var id = $j(this).attr("id");
				var name = $j(this).attr("name");
				$rootScope.filters.countries.push({'id':id,'name':name});
			});
			if ($rootScope.tabIndex == 1) {
				$rootScope.$broadcast("refresh-roaming-trends");
			}
		};
		
		var removeCounryFilter = function (id) {
			$j('#'+id).removeAttr('checked');
			$rootScope.filters.countries = $j.map($rootScope.filters.countries, function(obj) {
				return obj.id == id ? null : obj; 
			});
		};
		var removeAttributeFilter = function (attrId,catId ) {
			$j('#'+attrId + "_"+catId).removeAttr('checked');
			$rootScope.filters.attributes[attrId] = $j.map($rootScope.filters.attributes[attrId], function(obj) {
				return obj.catId == catId ? null : obj; 
			});
			if ($rootScope.filters.attributes[attrId].length == 0) {
				delete $rootScope.filters.attributes[attrId];
			}
		};
		
		var removePersonaFilter = function (id) {
			$j('#'+id).removeAttr('checked');
			$rootScope.filters.personas = $j.map($rootScope.filters.personas, function(obj) {
				return obj.id == id ? null : obj; 
			});
		};
		$rootScope.removeCounryFilter = function(id,refresh) {
			if(refresh) {
				removeCounryFilter(id);
				$rootScope.$broadcast("refresh-roaming-trends");
			} else {
				$j('#modal_'+id).hide();
				$rootScope.filters.removedFilters.push(function (){removeCounryFilter(id);})
			}	
		};
		
		$rootScope.removePersonaFilter = function(id,refresh) {
			if(refresh) {
				removePersonaFilter(id);
				$rootScope.$broadcast("refresh-roaming-trends");
			} else {
				$j('#modal_'+id).hide();
				$rootScope.filters.removedFilters.push(function (){removePersonaFilter(id);})
			}	
		};
		$rootScope.removeAttributeFilter = function(attrId,catId,refresh) {
			if(refresh) {
				removeAttributeFilter(attrId,catId);
				$rootScope.$broadcast("refresh-roaming-trends");
			} else {
				$j('#modal_'+attrId + "_"+catId).hide();
				$rootScope.filters.removedFilters.push(function (){removeAttributeFilter(attrId,catId);})
			}		
		};
		
		$rootScope.showModal = function (tabReqested) {
			$rootScope.filters.removedFilters = new Array();
			if (Object.keys($rootScope.filters.attributes).length != 0 
					|| $rootScope.filters.personas.length !=0
					|| $rootScope.filters.countries.length !=0 ) {
				$j('#onload-popup').modal('show');
				$rootScope.tabRequested = tabReqested;
			} else {
				$rootScope.tabIndex = tabReqested;
			}
			
		};
		
		$rootScope.applyFilters = function () {
			while( $rootScope.filters.removedFilters.length)
				$rootScope.filters.removedFilters.splice(0, 1)[ 0 ](); 
			$rootScope.tabIndex = $rootScope.tabRequested;
		};
		
		$rootScope.discardAllFilters = function () {
			$rootScope.filters.attributes = {};
			$rootScope.filters.tempAttributes = {};
			$rootScope.filters.personas = new Array();
			$rootScope.filters.countries = new Array();
			$j("aside input:checked").removeAttr('checked');
			
			$rootScope.tabIndex = $rootScope.tabRequested;
		};
		
	}]);
})();