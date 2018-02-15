(function() {
		
		app.factory("ContactView", function($resource) {
			return $resource("/api/pk/contact/get/getall", {}, {
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
		
})();
