app.controller('headerCtrl',['$scope','$rootScope','$state','$uibModal','Notification','LoginApi','$localStorage','$stateParams','$window','LogoutApi','SignUp',
                             function($scope,$rootScope, $state,$uibModal,Notification,LoginApi,$localStorage,$stateParams,$window,LogoutApi,SignUp){


	$scope.init = function(){
		$rootScope.user = $localStorage.user;
		$rootScope.createdUser = $localStorage.createdUser;
	}
	$scope.userLogin={};
	$scope.newUser={};
	$scope.defaultDashboard = function(){
		console.log('defaultDashboard');
		 $state.go('layout.home');
	}
	
	$scope.createAccount = function(obj){
		return SignUp.create(obj).$promise.then(function(data) {
			if(data.responseCode=='ERR0'){
				 $rootScope.createdUser =data.responseObject;
				 $localStorage.createdUser = data.responseObject;
			     // $state.go('layout.employee',param);
				  $scope.modalInstance.close('Hide My Modal');
				  Notification.success("Congrats! your account is created.");

			}else{
				Notification.error(data.responseDescription);		
			}
			
		},
		function(errResponse){
			Notification.error(errResponse);
		
		});		
	}
	$scope.loginModal = function () {
		$scope.tabShow =true;
		var newScope = $scope.$new()
	      return $scope.modalInstance = $uibModal.open({
	        templateUrl: 'app/component/login/login.html',
	        scope : newScope,
	        show: false,
	        backdropClick: false,
	        backdrop: true,
	        size: 'md', // sm, md, lg
	        windowClass: 'app-modal-window' 	
	      });
	    }
	    
	 $scope.cancel = function(){
	      $scope.modalInstance.close('cancel Button Clicked')
	   }
	  
	 
	 
	 $scope.validate = function(code){
		 var errorFound = true;
		 if($scope.newUser !=undefined){
		if(code==0){
				if($scope.newUser.mobile == null || $scope.newUser.mobile == undefined || $scope.newUser.mobile == ""){
					return errorFound = false;
				}
		}
		if(code==0){
			if($scope.newUser.password == null || $scope.newUser.password == undefined || $scope.newUser.password == ""){
				return	errorFound = false;
			}	
		}
	
    }
	return errorFound;	 
	 }
	 
	 $scope.logout = function(){
		 LogoutApi.get().$promise.then(function(data) {
			 $scope.user={};
			 $scope.createdUser={};
			 $localStorage.$reset();
			 $window.location.reload(); 
		 },
			function(errResponse){
				console.log('error found while logout')
			
			});	
	 }
	$scope.login = function(obj){
		
		
			if($scope.validate(0)){
				return LoginApi.save(obj).$promise.then(function(data) {
					if(data.responseCode=='ERR0'){
						 $rootScope.user =data.responseObject;
						 $localStorage.user = data.responseObject;
					     // $state.go('layout.employee',param);
						  $scope.modalInstance.close('Hide My Modal');
						  Notification.success("welcome to your world!");

					}else{
						Notification.error(data.responseDescription);		
					}
					
				},
				function(errResponse){
					Notification.error(errResponse);
				
				});		
				
			}
			
		}
	
	
	switch($stateParams.action){
	default:
	$scope.init();
	}
	

 }]);
