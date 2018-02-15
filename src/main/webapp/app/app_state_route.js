'use strict';
app.config(function($stateProvider, $urlRouterProvider) {
	console.log('inside config222');
    $urlRouterProvider.otherwise('/');
    
    $stateProvider
        .state('layout', {
            templateUrl: 'mainLayout.html'
        })
         .state('layout.home', {
            url: '/',
            controller :'dashboardCtrl',
            templateUrl : 'app/component/dashboard/dashboard.html'
        })
         .state('layout.login', {
            url: '/login',
            controller :'mainCtrl',
            templateUrl : 'app/component/login/login.html'
        })
        
        
        
});

app.run(function($rootScope, $state, $timeout) {

	    $rootScope.$on("$stateChangeStart", function(event, toState, toParams, fromState, fromParams) {
	        $rootScope.userLogged = localStorage.authorized;
	  

	        $rootScope.mainpreloder = true;
	       

	        if (($rootScope.userLogged == "true" || $rootScope.userLogged == true) && toState.name === "login") {
	            event.preventDefault();
	            $state.go('layout.dashboard');
	            return;
	        }

	        if (toState.name === "login") {
	            return;
	        }
	        if ($rootScope.userLogged == "false" || $rootScope.userLogged == false) {
	            event.preventDefault();
	            $state.go('login');
	            return;
	        }
	        
	    });
	    $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
	        $timeout(function() {
	            $rootScope.mainpreloder = false;
	        }, 1);
	    })
	});


