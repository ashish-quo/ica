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
				    ]);
})();

