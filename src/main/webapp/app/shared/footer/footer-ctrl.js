app.controller('footerCtrl',function($scope, $state	){

	console.log('called footer');
	
	$scope.contact = function(){
		$state.go('layout.contact');
	 }


 });
