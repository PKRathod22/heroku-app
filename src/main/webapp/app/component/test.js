app.controller('dashBoardCtrl',function($scope,HerokuDB){
	
	$scope.check={};
	HerokuDB.get(function(data) {
        if (data) {
            $scope.check = data.responseObject;
           
        } else {
       	 console.log("check  failed")
        }
   }, function(error) {
  	 console.log("check  failed : " + error)
   });
	
	
	
	
	
	
	
	
	
	
	
	
	
});