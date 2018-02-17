app.controller('MyProfileCtrl',function($scope,$state,$rootScope,Notification,UpdateUser){
	
	var userMaster = {};
	
	$scope.updateUserProfile = function(){
		console.log('update user details..');
		if($scope.authUser==undefined || $scope.authUser ==null){
			console.log("authuser is null..")
			return;
		}
		 userMaster = $scope.authUser;
		UpdateUser.update(userMaster).$promise.then(function(data){
			if(data.errorDescription=="ERR0"){
				$rootScope.authUser = data.responseContent;
				Notification.success('Record updated successfully !');
			}
			else{
				Notification.error("Record Updation Failed !");	
			}
		},function(error){
			Notification.error("service-unavailable.");	
			console.log("Record Updation Failed !" ,error);
		});
		
	}
	
	
});