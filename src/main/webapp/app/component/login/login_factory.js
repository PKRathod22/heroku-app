(function() {

	app.factory("LoginApi", function($resource) {
		return $resource("/api/v1/user/login", {}, {
			save : {
				method : 'POST'
			}
		});
	});

	
	app.factory("LogoutApi", function($resource) {
		return $resource("/api/v1/user/logout", {}, {
			get : {
				method : 'GET',
				params : {},
				isArray : false
			}
		});
	});
	
	app.factory("SignUp", function($resource) {
		return $resource("/api/v1/user/signup", {}, {
			create : {
				method : 'POST',
				params : {},
				isArray : false
			}
		});
	});
	
})();
