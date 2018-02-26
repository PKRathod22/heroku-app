(function() {

	
	app.factory("SendMoneyToUser", function($resource) {
		return $resource("/user/transaction/send/money", {}, {
			send : {
				method : 'POST'
			}
		});
	});
	

	app.factory("UserTransactionById", function($resource) {
		return $resource("/user/transaction/get/id/:id", {}, {
			get : {
				method : 'GET',
				params : {
					id : ''
				},
				isArray : false
			}
		});
	});
	
	


})();



