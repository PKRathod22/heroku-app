'use strict';
app.config(function($stateProvider, $urlRouterProvider) {
	console.log('inside config222');
	$urlRouterProvider.otherwise('/');

$stateProvider
.state('login', {
	url : '/login',
	controller:'mainCtrl',
	templateUrl : 'login.html'
})
     .state('layout', {
		templateUrl : 'mainLayout.html'
	})
	
	.state('layout.home', {
		url : '/',
		params : {
			obj : null
		},
		controller : 'dashboardCtrl',
		templateUrl : 'app/component/dashboard/dashboard.html'
	})

	.state('layout.about', {
		url : '/about',
		templateUrl : '/about.html'
	})
	
	.state('layout.courses', {
		url : '/courses',
		templateUrl : '/courses.html'
	})

	.state('layout.price', {
		url : '/price',
		templateUrl : '/price.html'
	})

	.state('layout.rightslid1', {
		url : '/right',
		templateUrl : '/sidebar-right.html'
	})

	.state('layout.contact', {
		url : '/contact',
		templateUrl : '/contact.html'
	})

	
	.state('layout.editUser', {
		url : '/editprofile',
		params : {
			obj : null,
			action : "edit"
		},
		controller : 'MyProfileCtrl',
		templateUrl : 'app/component/myprofile/edit-profile.html'
	})
	
	.state('layout.myprofile', {
		url : '/viewprofile',
		params : {
			obj : null,
			action : "view"
		},
		controller : 'MyProfileCtrl',
		templateUrl : 'app/component/myprofile/myprofile.html'
	})

});

app
		.run(function($rootScope, $state, $timeout,$location ) {

			$rootScope
					.$on(
							"$stateChangeStart",
							function(event, toState, toParams, fromState,
									fromParams) {
								$rootScope.userLogged = localStorage.authorized;

								
  if($rootScope.userLogged=="false" || $rootScope.userLogged==false 
		  && (toState.name === "layout.myprofile" || toState.name ==="layout.editUser")){
	  $location.path('/')
	  return;
  }
								
								

							});
			$rootScope.$on('$stateChangeSuccess', function(event, toState,
					toParams, fromState, fromParams) {
				$timeout(function() {
					$rootScope.mainpreloder = false;
				}, 100);
			})
		});
