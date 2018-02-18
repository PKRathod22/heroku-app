'use strict';
app.config(function($stateProvider, $urlRouterProvider) {
	console.log('inside config222');
	$urlRouterProvider.otherwise('/');

	$stateProvider

	.state('layout', {
		templateUrl : 'mainLayout.html'
	})

	.state('layout.home', {
		url : '/home',
		params : {
			obj : null
		},
		controller : 'dashboardCtrl',
		templateUrl : 'app/component/dashboard/dashboard.html'
	})

	.state('layout.login', {
		url : '/login',
		controller : 'mainCtrl',
		templateUrl : 'app/component/login/login.html'
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
		url : '/editprofile/:id',
		params : {
			id : null,
			action : "edit"
		},
		controller : 'MyProfileCtrl',
		templateUrl : 'app/component/myprofile/edit-profile.html'
	})

	.state('layout.myprofile', {
		url : '/viewprofile/:id',
		params : {
			id : null,
			action : "view"
		},
		controller : 'MyProfileCtrl',
		templateUrl : 'app/component/myprofile/myprofile.html'
	})

});

app
		.run(function($rootScope, $state, $timeout, $location) {

			$rootScope
					.$on(
							"$stateChangeStart",
							function(event, toState, toParams, fromState,
									fromParams) {

								$rootScope.userLogged = localStorage.authorized;

								if (($rootScope.userLogged == "true" || $rootScope.userLogged == true)
										&& toState.name === "layout.login") {
									event.preventDefault();
									$state.go(toState.name);
									return;
								}

								if (($rootScope.userLogged == "false" || $rootScope.userLogged == false)
										&& (toState.name === "layout.myprofile" || toState.name === "layout.editUser")) {
									$location.path('/home')
									return;
								}
								 

								if ($rootScope.userLogged == "false"
										|| $rootScope.userLogged == false || $rootScope.userLogged==undefined) {
									$state.go('layout.home');
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