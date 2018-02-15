app.controller('mainCtrl',function($scope, $state, $rootScope, $timeout,Notification,RegisterUser){
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
	
	$rootScope.clear = function() {
		console.log("Clear Method is called....!");
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



});
