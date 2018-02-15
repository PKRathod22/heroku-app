(function() {
		
		app.factory("UserGetById", function($resource) {
			return $resource("/user/get/id/:id", {}, {
				get : {
					method : 'GET',
					params : {
						id : ''
					},
					isArray : false
				}
			});
		});
		
		
		app.factory("RegisterUser", function($resource) {
			return $resource("/user/register/create", {}, {
				save : {
					method : 'POST'
				}
			});
		});
		
		
		app.factory("LoginUser", function($resource) {
			return $resource("/user/login", {}, {
				get : {
					method : 'POST'
				}
			});
		});
		
})();
