( function () {
	var app = angular.module("roamer-analytics",
			['app.main','app.sidebar','app.home','app.trends',
			 'app.microsegment','app.msdirective','app.utility',
			 'app.directives']) .factory('sessionTimeoutInterceptor', [function() {
				   return { response: function(response) {
				        if (angular.isString(response.data) && response.data.indexOf('perform_login') != -1)
				          window.location.reload();
				        return response;
				  } };
			 }
				    ]).  config(['$httpProvider', function($httpProvider) {
				      $httpProvider.interceptors.push('sessionTimeoutInterceptor');
				    }
				    ]).service('pendingRequests', function() {
				    	  var pending = [];
				    	  this.get = function() {
				    	    return pending;
				    	  };
				    	  this.add = function(request) {
				    	    pending.push(request);
				    	  };
				    	  this.remove = function(request) {
				    	    pending = _.filter(pending, function(p) {
				    	      return p.url !== request;
				    	    });
				    	  };
				    	  this.cancelAll = function() {
				    	    angular.forEach(pending, function(p) {
				    	      p.canceller.resolve();
				    	    });
				    	    pending.length = 0;
				    	  };
				    	})
				    	// This service wraps $http to make sure pending requests are tracked 
				    	.service('httpService', ['$http', '$q', 'pendingRequests', function($http, $q, pendingRequests) {
				    	  this.get = function(url,data) {
				    	    var canceller = $q.defer();
				    	    pendingRequests.add({
				    	      url: url,
				    	      canceller: canceller
				    	    });
				    	    //Request gets cancelled if the timeout-promise is resolved
				    	    var requestPromise = $http.get(url,data, { timeout: canceller.promise });
				    	    //Once a request has failed or succeeded, remove it from the pending list
				    	    requestPromise.finally(function() {
				    	      pendingRequests.remove(url);
				    	    });
				    	    return requestPromise;
				    	  }
				    	}]);
				   
})();

