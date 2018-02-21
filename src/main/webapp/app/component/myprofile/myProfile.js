app.controller('MyProfileCtrl', function($scope, $state, $rootScope,$timeout,
		Notification, UpdateUser, UserGetById, $stateParams,CommonService,GetAllRecentUser,GetDownlineUser) {

	var userMaster = {};

	$scope.defaultDashboard = function() {
		console.log('defaultDashboard');
		$state.go('layout.home');
	}
	$scope.login = function() {
		console.log('login tab pressed..');
		$state.go('layout.login');
		//$http.get("app/component/shopping/shopping.html");
	}

	$scope.about = function() {
		console.log('about cliked..');
		$state.go('layout.about');
	}
	$scope.courses = function() {
		console.log('cources cliked..');
		$state.go('layout.courses');
	}
	$scope.price = function() {
		console.log('cources cliked..');
		$state.go('layout.price');
	}

	$scope.link1 = function() {
		console.log('rightslid1 cliked..');
		$state.go('layout.rightslid1');
	}
	$scope.contact = function() {
		console.log('contact cliked..');
		$state.go('layout.contact');
	}

	$scope.myprofile = function(id) {
		console.log('view profile pressed ::::::- ' + id);
		$state.go('layout.myprofile', {
			id : id
		});
	}
	
	$scope.editProfile = function(id) {
		console.log('edit profile pressed ::::::- ' + id);
		$state.go('layout.editUser', {
			id : id
		});
	}

	$scope.mydownline  = function(id){
		console.log('mydownline pressed ::::::- ' + id);
		GetDownlineUser.get({id:id}).$promise.then(function(data) {
			if (data.errorDescription == "ERR0") {
				$rootScope.downlineUserList = data.responseContents;
				console.log("getdownlineUser list :::::", $scope.downlineUserList);
				$state.go('layout.mydownline', {
					id : id
				});

			}}, function(error) {
			Notification.error("service-unavailable.");
			console.error("exception found to downline user list !", error);
		});

	
    }
	
	$scope.approval = function(){
		$state.go('layout.approve');
	}
	
	
	$scope.updateUserProfile = function() {
		console.log('update user details..');
		if ($scope.authUser == undefined || $scope.authUser == null) {
			console.log("authuser is null..")
			return;
		}
		userMaster = $scope.authUser;
		UpdateUser.update(userMaster).$promise.then(function(data) {
			if (data.errorDescription == "ERR0") {
				$rootScope.authUser = data.responseContent;
				Notification.success('Record updated successfully !');
			} else {
				Notification.error("Record Updation Failed !");
			}
		}, function(error) {
			Notification.error("service-unavailable.");
			console.log("Record Updation Failed !", error);
		});

	}

	$scope.recentRegisteredList = function(){
		console.log('recent user list called..')
		GetAllRecentUser.get().$promise.then(function(data) {
			if (data.errorDescription == "ERR0") {
				$scope.recentUserList = [];
				$rootScope.recentUserList = data.responseContents;
				console.log("recentUserList ", $scope.recentUserList);
				if($scope.authUser !=null && $scope.authUser.status!=null){
					
					angular.forEach($rootScope.recentUserList, function(value, index){
						/*if (value.status == 'INPROGRESS') {
							$rootScope.myColor = '#ffb100';
						} if (value.status == 'BLOCKED') {
							$rootScope.myColor = '#ff2d29';
						}if (value.status == 'ACTIVE') {
							$rootScope.myColor = '#0cb90c';
						}*/
					console.log('value ::::::::::::'+value+':::::index::::::::'+index)
					   });
					
						
				}
				$state.go('layout.recentjoin');

			} else {
				Notification.error("failed to get user list !");
			}
		}, function(error) {
			Notification.error("service-unavailable.");
			console.error("failed to get user list !", error);
		});

	}
	
	$scope.fetchByDistributerId = function(id){
		console.log("fetchByDistributerId pressed.. ", id);
		$scope.getByDistributerId(id,true); 	
	}
	
	$scope.getByDistributerId = function(id,fetchType) {
		console.log("getById ", id);
		UserGetById.get({
			id : id
		}, function(data) {
			
			if (data.errorDescription == "ERR0") {
				console.log("Successfully  getting user distrbuter id.", data)
				$scope.authUser = data.responseContent;
               if($scope.authUser !=null && $scope.authUser.dobYear!=null && typeof $scope.authUser.dobYear=="string"){
            	   JSON.stringify($scope.authUser.dobYear);
               }
				if($scope.authUser !=null && $scope.authUser.status!=null){
					if ($scope.authUser.status == 'INPROGRESS') {
						$scope.myColor = '#ffb100';
					}else if ($scope.authUser.status == 'BLOCKED') {
						$scope.myColor = '#ff2d29';
					}else if ($scope.authUser.status == 'ACTIVE') {
						$scope.myColor = '#0cb90c';
					}	
				}
				
				if($scope.authUser!=null && $scope.authUser.currentLoginDate!=null){
					$scope.lastLoginDate = $rootScope.dateToString($scope.authUser.currentLoginDate);	
				}
				
			} 
			if(fetchType==true && data.responseContent!=null && !CommonService.isObjectEmpty(data.responseContent) ){
				$scope.signUpUser = {};
				$scope.signUpUser = data.responseContent;
				Notification.success('Distributer Id is valid !');
			}else if(fetchType==true && data.responseContent==null){
				Notification.error('Distributer Id is not valid !');
			}
			$scope.setOldDataVal();

		}, function(error) {
			Notification.error("service-unavailable.");
			console.log("Error while getting user distruter id.", error)
		});

	}

	$scope.updateStatus = function(){
		$scope.userMaster = $scope.signUpUser;
		UpdateUser.update($scope.userMaster).$promise.then(function(data){
			if(data.errorDescription=="ERR0"){
				Notification.success('Record updated successfully !');
			}
			else{
				Notification.error('Record updation failed !');	
			}
		},function(error){
			Notification.error("service-unavailable.");
		});
	
	}
	
	$scope.onchangeStatus = function(){
		if ($scope.signUpUser.status == 'INPROGRESS') {
			$scope.myColor = '#ffb100';
		}else if ($scope.signUpUser.status == 'BLOCKED') {
			$scope.myColor = '#ff2d29';
		}else if ($scope.signUpUser.status == 'ACTIVE') {
			$scope.myColor = '#0cb90c';
		}	
	}
	
	$scope.setOldDataVal = function() {
		$scope.oldData = JSON.stringify($scope.authUser);
	}

	$scope.$on('editUserEvent', function(events, args) {
		$rootScope.unfinishedData = $scope.authUser;

	})

	$scope.$on('viewUserEvent', function(events, args) {
		$rootScope.unfinishedData = $scope.authUser;

	})

	$scope.$on('editUserEventReload', function(e, confirmation) {
		confirmation.message = "";
		localStorage.reloadFormData = JSON.stringify($scope.authUser);
		localStorage.isUserReloaded = "YES";
		e.preventDefault();
	});

	
	 $scope.$on('signupUserEvent', function(events, args) {
			$rootScope.unfinishedData = $scope.signUpUser;

	  })
	 
	 $scope.$on('signupUserEventReload', function(e, confirmation) {
			confirmation.message = "";
			localStorage.reloadFormData = JSON.stringify($scope.signUpUser);
			localStorage.isUserReloaded = "YES";
			e.preventDefault();
		});

	
	$scope.isReloaded = localStorage.isUserReloaded;

	
	
	if ($stateParams.action == "edit" || $stateParams.action == "view") {
		if ($scope.isReloaded == "YES") {
			$scope.isReloaded = "NO";
			localStorage.isUserReloaded = "NO";
			$scope.authUser = JSON.parse(localStorage.reloadFormData);
			console.log("User master after Reload..." + $scope.authUser);
			$scope.oldData = "";
		} else {
			$scope.getByDistributerId($stateParams.id);
			console.log("User master NotReload...")
		}
	}
	
	 if ($stateParams.action == "signup") {
			if ($scope.isReloaded == "YES") {
				$scope.isReloaded = "NO";
				localStorage.isUserReloaded = "NO";
				$scope.signUpUser = JSON.parse(localStorage.reloadFormData);
				console.log("signUpUser master after Reload..." + $scope.signUpUser);
				$scope.oldData = "";
			} else {
				console.log("signUpUser NotReload...")
			}
		}

});