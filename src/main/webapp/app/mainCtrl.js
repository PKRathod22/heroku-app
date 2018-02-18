app.controller('mainCtrl',function($scope, $state, $window ,$rootScope, $timeout,$location,$stateParams ,Notification,UserLogout,ObjService,RegisterUser,UserGetById,LoginUser){
console.log('called main..');

$scope.gender = ["Male","Female"];
$scope.errorMap = new Map();
   $scope.userMaster = {};
   $scope.loginFlag = true;
   $scope.registerFlag = false;
   
   $scope.registerTab = function(){
	   $scope.registerFlag = true; 
	   $scope.loginFlag = false;
	   $scope.userMaster = {};
   }
   
   $scope.loginTab = function(){
	   $scope.loginFlag = true;
	   $scope.registerFlag = false;
	   $scope.userMaster = {};
	   $scope.errorMap = new Map();;
   }
   
   $scope.validateRegister = function(){
	   $scope.errorMap = new Map();
		
	   if( $scope.userMaster!=null &&  $scope.userMaster.userName == null){
			$scope.errorMap.put('userName', "required");
			return false;
		}if( $scope.userMaster!=null &&  $scope.userMaster.mobileNumber == null){
			$scope.errorMap.put('mobileNumber', "required");
			return false;
		}if( $scope.userMaster!=null &&  $scope.userMaster.gender == null){
			$scope.errorMap.put('gender', "required");
			return false;
		}if( $scope.userMaster!=null &&  $scope.userMaster.password == null){
			$scope.errorMap.put('password', "required");
			return false;
		}
		return true;
   }
	$scope.register=function(){
		console.log('register called..');
		
		if($scope.validateRegister()){
			RegisterUser.save($scope.userMaster).$promise.then(function(data){
				if(data.errorDescription=="ERR0"){
					$scope.loginTab();
					Notification.success('thanks for registeration you are welcome !');
				}
				else{
					Notification.error(data.responseDescription);	
				}
			},function(error){
				if(data.responseDescription!=null)
				Notification.error(data.responseDescription);	
				$log.info("newProducts Saving Failed : " ,error);
			});
		}
	}
	
   
   $scope.login = function(){
	   console.log("login")
	   $scope.errorMap =new Map();
	   if($scope.validate($scope.userMaster)){
		   LoginUser.get($scope.userMaster).$promise.then(function(data){
				if(data.errorDescription=="ERR0"){
					//Notification.success('valid user!');
					$rootScope.userLogged = true;
	                localStorage.authorized=true;
	                $rootScope.authUser = {};
	                $rootScope.authUser = data.responseContent;
	                localStorage.setItem('authUser', JSON.stringify($rootScope.authUser));
	               // ObjService.set($rootScope.authUser);
	                //location.href = '/index.html';
	                var id = $rootScope.authUser.distributerId;
					$state.go('layout.myprofile',{ id : id});
				}
				else{
					Notification.error('user id or password is wrong !');	
				}
			},function(error){
				$rootScope.userLogged = false;
				Notification.error("internal server error !");	
			});   
	   }else{
		   console.log("validation failed..") 
	   }
	   
   }
   
   $scope.logout = function(){
	   console.log('logout called..')
	   UserLogout.get(function(data) {
		   localStorage.authUser = null;
		   localStorage.authorized=false;
		   $rootScope.authUser = null;
		   $window.location.reload();
		   $location.path('/home');		   
		   
	   });
	}
	
   
   $scope.validate = function(){
	   
	   if($scope.userMaster.distributerId==null){
		   $scope.errorMap.put('userId', "user id required");
			$rootScope.navigateToNextField('userId'); 
			 return false;
	   }
	   if($scope.userMaster.password==null){
		   $scope.errorMap.put('password', "password id required");
			$rootScope.navigateToNextField('password');
			 return false;
	   }
	   return true;
   }
   
  
	
	
	$rootScope.back = function() {
		console.log(" back to home....!");
		 $location.path('/home')
	}
		
		
	$rootScope.navigateToNextField = function(id) {
		$timeout(function(){
			if(id!=undefined && id != null) {
				var docElement = document.getElementById(id);
				if(docElement != undefined && docElement != null){
					docElement.focus();
				}
			}
		})
     }

	
	 $rootScope.stateEventMap = new Map();
	 $rootScope.stateEventMap.put("layout.editUser", "editUserEvent");
	 $rootScope.stateEventMap.put("layout.myprofile", "viewUserEvent");

		
		

});