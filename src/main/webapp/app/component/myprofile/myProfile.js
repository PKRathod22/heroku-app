app.controller('MyProfileCtrl',function($scope,$state,$rootScope,Notification,UpdateUser,UserGetById,$stateParams){
	
	var userMaster = {};
	
	
	$scope.months = ["Month","1", "2", "3","4","5","6","7","8","9","10","11","12"];
	$scope.days = ["Day","1", "2", "3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29",
	              "30","31"];

	$scope.defaultDashboard = function(){
		console.log('defaultDashboard');
		 $state.go('layout.home');
	}
	$scope.login = function(){
		console.log('calling login');
	    $state.go('layout.login');
		//$http.get("app/component/shopping/shopping.html");
		
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
	 
	 $scope.myprofile = function(id){
		console.log('view profile pressed ::::::- '+id);
		 $state.go('layout.myprofile',{id:id}); 
	 }
	
	 $scope.editProfile =  function(id){
			console.log('edit profile pressed ::::::- '+id);
			$state.go('layout.editUser',{id:id});
		}
	
	
	
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
	
	$scope.getByDistributerId = function(id) {
		console.log("getById ", id);
		
		UserGetById.get({
  			id : id
  		}, function(data) {
  			if (data.errorDescription =="ERR0"){
  				console.log("Successfully  getting user distrbuter id.", data)
  				$scope.authUser = data.responseContent;
  				
  				if($scope.authUser.status=='INPROGRESS'){
  					$scope.myColor='#ffb100';
  				}
  				if($scope.authUser.status=='BLOCKED'){
  					$scope.myColor='#ff2d29';
  				}if($scope.authUser.status=='ACTIVE'){
  					$scope.myColor='#0cb90c';
  				}
  			}else{
  				Notification.error('failed.');
  			}
   			$scope.setOldDataVal();	

  		}, function(error) {
  			console.log("Error while getting user distruter id.", error)
  		});
		
	}
	
	$scope.setOldDataVal = function(){
        $scope.oldData = JSON.stringify($scope.authUser);
    }
	
	
	$scope.$on('editUserEvent', function(events, args) {
		$rootScope.unfinishedData = $scope.authUser;
		
     })
	
     $scope.$on('viewUserEvent', function(events, args) {
		$rootScope.unfinishedData = $scope.authUser;
		
     })
     
     $scope.$on('editUserEventReload', function (e, confirmation) {
        confirmation.message = "";
        localStorage.reloadFormData = JSON.stringify($scope.authUser) ;
        localStorage.isUserReloaded = "YES";
        e.preventDefault();
    });
	
     $scope.$on('viewUserEventReload', function (e, confirmation) {
        confirmation.message = "";
        localStorage.reloadFormData = JSON.stringify($scope.authUser) ;
        localStorage.isUserReloaded = "YES";
        e.preventDefault();
    });
	
	$scope.isReloaded = localStorage.isUserReloaded;

	if ($stateParams.action=="edit" || $stateParams.action=="view") {

	if($scope.isReloaded == "YES") {
		$scope.isReloaded = "NO";
		localStorage.isUserReloaded = "NO";
		$scope.authUser  = JSON.parse(localStorage.reloadFormData);
		console.log("User master after Reload..."+$scope.authUser);
		$scope.oldData = "";
	} else {
		$scope.getByDistributerId($stateParams.id);
		console.log("User master NotReload...")
	}	

	
	}
	
});