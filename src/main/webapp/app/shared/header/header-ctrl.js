
app.controller('headerCtrl',function($scope,$rootScope, $state, $http,$timeout){

	console.log('called header');

	$scope.defaultDashboard = function(){
		console.log('defaultDashboard');
		 $state.go('layout.home');
	}
	$scope.login = function(){
		console.log('calling login');
	    $state.go('layout.login');
		//$http.get("app/component/shopping/shopping.html");
		
	 }
   
	 $scope.addProduct = function(){
		 console.log('addProduct pressed..');
		$state.go('layout.addProduct');
	 }
	
	

 });
