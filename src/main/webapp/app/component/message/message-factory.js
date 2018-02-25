(function() {

app.factory("SendEnquiry", function($resource) {
			return $resource("/user/message/sendenquiry", {}, {
				save : {
					method : 'POST'
				}
			});
		});


})();
		