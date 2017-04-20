app.controller('mainCtrl',function($scope, $rootScope, $timeout){
console.log('called main..');
		
		
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
