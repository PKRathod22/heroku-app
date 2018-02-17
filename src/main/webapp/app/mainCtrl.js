app.controller('mainCtrl',function($scope, $state, $window ,$rootScope, $timeout,$location,$stateParams ,Notification,UserLogout,ObjService,RegisterUser,UserGetById,LoginUser){
console.log('called main..');

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
	$scope.register=function(){
		console.log('register called..');
		$scope.errorMap = new Map();
		if( $scope.userMaster!=null &&  $scope.userMaster.userName == null){
			$scope.errorMap.put('userName', "name is required");
			$rootScope.navigateToNextField('name');
			return;
		}
		
		
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
	
   
   $scope.getById = function(id) {
		console.log("getById ", id);
		
		UserGetById.get({
  			id : id
  		}, function(data) {
  			if (data.responseCode =="ERR0"){
  				console.log("Successfully  getting user distrbuter id.", data)
  				$scope.userMaster = data.responseObject;
  			}else{
  				Notification.error('user id or password is wrong !');
  			}
  			
  		}, function(error) {
  			console.log("Error while getting user distruter id.", error)
  		});
		
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
	                var obj ={};
					$state.go('layout.myprofile',{ obj : $rootScope.authUser});
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
		   $window.location.reload();
		  // $location.path('/');		   
		   
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
		 $location.path('/')
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

	$scope.view = function(id){
		$scope.getById(id);
	}
	
	switch ($stateParams.action) {
	case "view":
		console.log("My profile View Page", $stateParams.id);
		$rootScope.unfinishedData = undefined;

		if ($stateParams.id != undefined && $stateParams.id != null) {
			$scope.view($stateParams.id);
		}
		
		
		break;
	default:
	}

});
