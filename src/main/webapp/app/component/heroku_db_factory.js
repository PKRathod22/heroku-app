(function() {
	app.factory("HerokuDB", function($resource) {
		return $resource("/api/v1/getall", {}, {
			get : {
				method : 'GET',
				params : {},
				isArray : false
			}
		});
	});
	
})();