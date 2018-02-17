
app.controller('headerCtrl',function($scope,$rootScope, $state, $http,$timeout){

	console.log('called header');

	$scope.defaultDashboard = function(){
		console.log('defaultDashboard');
		 $state.go('layout.home');
	}
	$scope.login = function(){
		console.log('calling login');
	    $state.go('login');
		//$http.get("app/component/shopping/shopping.html");
		
	 }
   
	$scope.editProfile =  function(){
		console.log('edit profile pressed..');
		$state.go('layout.editUser');
	}
	
	
	 $scope.about = function(){
		 console.log('about cliked..');
		$state.go('layout.about');
	 }
	 $scope.courses = function(){
		 console.log('cources cliked..');
		$state.go('layout.courses');
	 }
	 $scope.price = function(){
		 console.log('cources cliked..');
		$state.go('layout.price');
	 }
	
	 $scope.link1 = function(){
		 console.log('rightslid1 cliked..');
		$state.go('layout.rightslid1');
	 }
	 $scope.contact = function(){
		 console.log('contact cliked..');
		$state.go('layout.contact');
	 }
	 
	 $scope.myprofile = function(){
		 $state.go('layout.myprofile'); 
	 }
	
	

 });
