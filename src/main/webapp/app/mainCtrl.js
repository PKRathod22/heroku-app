app.controller('mainCtrl',function($scope, $state, $window ,$rootScope, $timeout,$location,$stateParams, CommonService, Notification,UserLogout,ObjService,RegisterUser,UserGetById,LoginUser){
console.log('called main..');

$rootScope.gender = ["Male","Female"];
$rootScope.genError ="service-unavailable"
$rootScope.months = [ "Month", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
      			"AUG", "SEP", "OCT", "NOV", "DEC" ];
$rootScope.days = [ "Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
      			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
      			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" ];

$rootScope.years = CommonService.years(1970);

$rootScope.yesNo =["Yes","No"];
$rootScope.legPosition = ["Left","Right"];
$rootScope.status =["ACTIVE","BLOCKED","INPROGRESS"];
$rootScope.package_name = ["Free Package - 0.00 (FBV :0.00)",
                          "Flywin Premium package- 4399.00 (FBV :50.00)",
                          "Flywin crown package-9999.00 (FBV :50.00)",
                          "Flywin Silver package-12999.00 (FBV :100.00)",
                          "Flywin Diamond package-18999.00 (FBV :100.00)"];
$rootScope.transactionType = ["Credited","Debited"];
$rootScope.transactionMode = ["By Cash","NetBanking","Tez","PhonePay","BHIM","Paytm","PayZapp","Others"];

$rootScope.maritalStatus=["Single","In Relation","Married","UnMarried","Complicated"];

$rootScope.differnceBetweenTwoDates = function(date){
	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	var createdDate = new Date(date);
	console.log("createdDate:",createdDate)
	var thirty_days_from_CreatedDate = new Date((createdDate).getTime() + 30*24*60*60*1000);
	$rootScope.thirtyDays =thirty_days_from_CreatedDate;
	console.log("thirty_days_from_CreatedDate convert:",thirty_days_from_CreatedDate)
	//var time = new Date();
	//time.setDate(time.getDate()+30);
	//alert(time);
	// $rootScope.leftDate= Math.round(Math.abs((thirty_days_from_CreatedDate.getTime() - createdDate.getTime())/(oneDay)));
	 $rootScope.leftDate= Math.round(Math.abs((new Date().getTime() - thirty_days_from_CreatedDate.getTime())/(oneDay)));
}

$rootScope.dateToString = function(dateObj){
 return	new Date(dateObj).toDateString();
}

$rootScope.beforeSpace = function(str){
	return str = str.trim().split(/\s(.+)/)[0];  //everything before the first space
}

$rootScope.prod = {imagePaths: []};
$rootScope.prod.imagePaths = [
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_cheesecake_brownie.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_cheesecake_brownie.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_lemon.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_lemon.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_donut.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_donut.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_caramel.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_caramel.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_cheesecake_brownie.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_cheesecake_brownie.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_lemon.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_lemon.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_donut.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_donut.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_caramel.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_caramel.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_cheesecake_brownie.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_cheesecake_brownie.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_lemon.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_lemon.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_donut.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_donut.jpg' },
  	{ custom: 'http://flexslider.woothemes.com/images/kitchen_adventurer_caramel.jpg', thumbnail: 'http://flexslider.woothemes.com/images/kitchen_adventurer_caramel.jpg'}
  ];



$scope.errorMap = new Map();
   $scope.userMaster = {};
   $scope.loginFlag = true;
   $scope.registerFlag = false;
   
   $scope.registerTab = function(){
	   $scope.errorMap = new Map();
	   $scope.registerFlag = true; 
	   $scope.loginFlag = false;
	   $scope.userMaster = {};

	   $scope.userMaster.gender ="Male";
       $scope.userMaster.packageName="Free Package - 0.00 (FBV :0.00)";   
       $scope.userMaster.legPosition ="Left";
       $scope.userMaster.paymentStatus ="No";
   }
   
   $scope.loginTab = function(){
	   $scope.loginFlag = true;
	   $scope.registerFlag = false;
	   $scope.userMaster = {};
	   $scope.errorMap = new Map();
   }
   
   $scope.validateRegister = function(){
	   $scope.errorMap = new Map();
		
	   if(  $scope.userMaster.userName == null){
			$scope.errorMap.put('userName', "required");
			return false;
		}if( $scope.userMaster.mobileNumber == null){
			$scope.errorMap.put('mobileNumber', "required");
			return false;
		}if( $scope.userMaster.gender == null){
			$scope.errorMap.put('gender', "required");
			return false;
		}if(  $scope.userMaster.password == null){
			$scope.errorMap.put('password', "required");
			return false;
		}if($scope.userMaster.paymentStatus=='Yes'){
			$scope.userMaster.paymentStatus = true;
		}else{
			$scope.userMaster.paymentStatus = false;
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
					Notification.error('Not Registered ! please try again after some time.');	
				}
			},function(error){
				Notification.error("service-unavailable.");
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
	                
	                if($rootScope.authUser.userType=='admin'&& $rootScope.authUser.status=='ACTIVE'){
						$state.go('layout.myprofile',{ id : id});
	                }else{
	                	$state.go('layout.myprofile',{ id : id});
	                }
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
		   
	   },function(error){
		   Notification.error("service-unavailable.");
	   });
	}
	
   
   $scope.validate = function(){
	   
	   if($scope.userMaster.distributerId==null){
		   $scope.errorMap.put('userId', "user id required");
			 return false;
	   }
	   if($scope.userMaster.password==null){
		   $scope.errorMap.put('password', "password is required");
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
	 $rootScope.stateEventMap.put("layout.signup", "signupUserEvent");


	
	 
	 
	 
	 

});
