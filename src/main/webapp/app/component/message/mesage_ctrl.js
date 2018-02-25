app.controller('MessageCtrl',function($rootScope,$scope,Notification,SendEnquiry){
	
	$scope.userMessage = {};
	
	$scope.sendEnquiry = function(){
		if($scope.validateMessage()){
			SendEnquiry.save($scope.userMessage).$promise.then(function(data){
				if(data.errorDescription=="ERR0"){
					Notification.success('Thanks for your response ! we will always here to help you.');
				}else{
					Notification.error('sending failed..')
				}
			},function(error){
				Notification.error($rootScope.genError);
			} );	
		}
	}
	
	$scope.validateMessage = function(){
		   $scope.errorMap = new Map();
		   if(  $scope.userMessage.name == null){
				$scope.errorMap.put('name', "required");
				return false;
			}
		   if(  $scope.userMessage.email == null){
				$scope.errorMap.put('email', "required");
				return false;
			}
		   if(  $scope.userMessage.phoneNumber == null){
				$scope.errorMap.put('phone', "required");
				return false;
			}
		   if(  $scope.userMessage.message == null){
				$scope.errorMap.put('message', "required");
				return false;
			}
		   return true;
	}
	
	
	
	
	
});